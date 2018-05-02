package io.dama.vs.iotprotocol;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/* 
 * unary rpc:
 * ==========
 * 
 * Der Sensor ändert ständig und in zufälligem Rhythmus seinen Zustand. Der SensorBlockingClient 
 * hat keine Möglichkeit sich diesem Rhythmus anzupassen. Stattdessen fragt er in seiner eigenen 
 * Geschwindigkeit ständig (in der while-Schleife in main) den aktuellen Sensorwert ab. Man sieht 
 * an der Ausgabe, dass mehrfach derselbe Wert ausgegeben wird. Nach einer gewissen Zeit wird der
 * nebenläufige Betrieb des Sensors durch Aufruf der entfernten switchOff-Methode gestoppt. Der
 * Sensor wird jedoch weiter aus der Endlosschleife heraus abgefragt. Der Zustand des heruntergefahrenen
 * Sensors ändert sich nicht mehr. Es wird also immer weiter derselbe Wert vom Server zurückgeliefert. 
 * 
 */
public class SensorBlockingClient {

	private final ManagedChannel channel;
	private final IoTProtocolGrpc.IoTProtocolBlockingStub blockingStub;

	// da mehrfach verwendet: Erzeugen eines leeren Requests
	private static final Iotprotocol.empty emptyRequest = Iotprotocol.empty.newBuilder().build();

	public SensorBlockingClient(String host, int port) {
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
		SensorBlockingClient client = new SensorBlockingClient("localhost", Conf.PORT);

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

		try {
			// unary rpc: in einer Schleife Sensor abfragen und vom Server gemeldete
			// Observation ausgeben
			while (true) {
				Thread.sleep(500);
				Iotprotocol.Observation response = client.blockingStub.getObservation(emptyRequest);
				System.out.println("Messung: " + response.getValue());
			}
		} finally {
			// Verbindung zum Server abbauen
			client.shutdown();
		}
	}
}