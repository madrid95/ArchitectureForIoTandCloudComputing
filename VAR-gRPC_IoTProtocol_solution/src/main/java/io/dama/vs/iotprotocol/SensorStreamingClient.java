package io.dama.vs.iotprotocol;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.dama.vs.iotprotocol.Iotprotocol.Observation;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/* 
 * server-side streaming rpc:
 * ==========================
 * 
 * Der Sensor ändert ständig und in zufälligem Rhythmus seinen Zustand. Der SensorStreamingClient 
 * wird genau in diesem Rhythmus über die Änderungen des Zustands des Sensors informiert (über 
 * den Iterator in main). Man sieht an der Ausgabe, dass jedesmal genau ein veränderter Wert 
 * ausgegeben wird. Nach einer gewissen Zeit wird der nebenläufige Betrieb des Sensors durch Aufruf 
 * der entfernten switchOff-Methode gestoppt. Der Client wird darüber durch das Ende des Iterators
 * informiert. Der SensorStreamingClient baut daraufhin die Verbindung zum Server ab und terminiert. 
 * 
 */
public class SensorStreamingClient {

	private final ManagedChannel channel;
	private final IoTProtocolGrpc.IoTProtocolBlockingStub blockingStub;

	// da mehrfach verwendet: Erzeugen eines leeren Requests
	private static final Iotprotocol.empty emptyRequest = Iotprotocol.empty.newBuilder().build();

	public SensorStreamingClient(String host, int port) {
		this.channel = ManagedChannelBuilder//
				.forAddress(host, port)//
				.usePlaintext(true)// unverschlüsselt, um TLS-Zertifikate zu vermeiden
				.build();
		this.blockingStub = IoTProtocolGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {
		// mit Server verbinden
		SensorStreamingClient client = new SensorStreamingClient("localhost", Conf.PORT);

		// Sensor-Thread nach 10 Sekunden "von außen" abschalten, indem nebenläufig 10
		// Sek. gewartet und dann die RPC-Methode switchOff aufgerufen wird, die
		// bewirkt, dass der Sensor-Thread anhält. Der Server läuft weiter und gibt auf
		// Anfrage den letzten Sensorwert zurück.
		Thread sensorKillerThread = new Thread(() -> {
			try {
				Thread.sleep(10000);
				client.blockingStub.switchOff(emptyRequest);
			} catch (InterruptedException e) {
				// unhandled
			}
		});
		sensorKillerThread.start();

		// server-side streaming rpc: Stream öffnen und neue vom Server gemeldete
		// Observations ausgeben

		// RPC-Methoden, die einen Stream zurückliefern, müssen mit einem Iterator
		// (typisiert auf den Rückgabetyp der RPC-Methode) verwendet werden. Solange der
		// Stream noch nicht abgeschlossen ist, kommen Werte vom Iterator.
		try {
			for (Iterator<Observation> i = client.blockingStub.streamObservations(emptyRequest); i.hasNext();) {
				System.out.println(i.next().getValue());
			}
		} finally {
			// Verbindung zum Server abbauen
			client.shutdown();
		}
	}
}