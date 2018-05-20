package io.dama.vs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.dama.vs.hase.Hase.Telegram;
import io.dama.vs.sensor.Sensor;
import io.dama.vs.sensor.SensorObserver;
import io.dama.vs.hase.HaSeServiceGrpc.HaSeServiceImplBase;
import io.dama.vs.hase.Hase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class Service extends HaSeServiceImplBase implements SensorObserver {
	private static final Service instance = new Service();
	private final Sensor sensor = new Sensor();
	private List<StreamObserver<Telegram>> streamClients = new ArrayList<>();

	@Override
	public void notify(Hase.Telegram observation) {
		for (StreamObserver<Hase.Telegram> client : this.streamClients) {
			client.onNext(observation);
		}
	}

	@Override
	public void getSensorId(Hase.Empty in, StreamObserver<Hase.Sensor> out) {
		out.onNext(Hase.Sensor.newBuilder().setSensorId(sensot.getSensorID()).build());
		out.onCompleted();
	}

	@Override
	public void switchOff(Hase.Sensor in, StreamObserver<Hase.Boolean> out) {
		// this.sensor stoppen
		this.sensor.stopSensor();
		// alle stremenden Clients über das Ende der Streams benachrichtigen
		out.onNext(Hase.Boolean.newBuilder().setResult(true).build());
		// "true" zurückgeben
		out.onCompleted();
	}

	@Override
	public void getObservation(Hase.Sensor in, StreamObserver<Hase.Telegram> out) {
		// aktuelle Observation von this.sensor holen und zurückgeben
		out.onNext(sensor.getObservation());
		out.onCompleted();
	}

	@Override
	public void streamObservations(Hase.Sensor in, StreamObserver<Hase.Telegram> out) {
		// Stream-Cient der Liste this.streamClients hinzufügen
		this.streamClients.add(out);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {

		instance.sensor.register(instance);
		instance.sensor.start();

		Server server = ServerBuilder.forPort(Conf.PORT).addService(instance).build();
		server.start();
		server.awaitTermination();
	}

}
