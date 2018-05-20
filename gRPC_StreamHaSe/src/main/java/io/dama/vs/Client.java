package io.dama.vs;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import io.dama.vs.hase.HaSeServiceGrpc;
import io.dama.vs.hase.Hase;
import io.dama.vs.hase.Hase.Telegram;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

	private final ManagedChannel channel;
	private final HaSeServiceGrpc.HaSeServiceBlockingStub blockingStub;

	public Client(String host, int port) {
		this.channel = ManagedChannelBuilder//
				.forAddress(host, port)//
				.usePlaintext(true)// unverschlüsselt, um TLS-Zertifikate zu vermeiden
				.build();
		this.blockingStub = HaSeServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client("localhost", Conf.PORT);
		Thread sensorKillerThread = new Thread(() -> {
			try {
				Thread.sleep(12000);
				client.blockingStub.switchOff(Hase.Sensor.newBuilder().build());
			} catch (InterruptedException e) {
				// unhandled
			}
		});
		sensorKillerThread.start();
		Hase.Sensor sensor = client.blockingStub.getSensorId(Hase.Empty.newBuilder().build());
		System.out.println("Messung 1: " + client.blockingStub.getObservation(sensor).toString());
		System.out.println("Messung 2: " + client.blockingStub.getObservation(sensor).toString());

		try {
			for (Iterator<Telegram> i = client.blockingStub.streamObservations(sensor); i.hasNext();) {
				System.out.println("Weitere Messung: " + i.next().toString());
			}
		} finally {
			client.shutdown();
		}
	}
}