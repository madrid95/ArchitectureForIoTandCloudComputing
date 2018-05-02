package io.dama.vs.iotprotocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.dama.vs.sensor.Sensor;
import io.dama.vs.sensor.SensorObserver;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class SensorServer extends IoTProtocolGrpc.IoTProtocolImplBase implements SensorObserver {
	private static final SensorServer instance = new SensorServer();
	private final Sensor sensor = new Sensor(Conf.INITIAL);
	private List<StreamObserver<Iotprotocol.Observation>> streamClients = new ArrayList<>();

	// da mehrfach verwendet: Erzeugen einer leeren Response
	private static final Iotprotocol.empty emptyResponse = Iotprotocol.empty.newBuilder().build();

	/*
	 * Wird von sensor aufgerufen, wenn dessen Wert sich ändert.
	 * 
	 * @see io.dama.vs.sensor.SensorObserver#notify(float)
	 */
	@Override
	public void notify(float value) {
		Iotprotocol.Observation currentObservation = Iotprotocol.Observation.newBuilder().setValue(value).build();
		for (StreamObserver<Iotprotocol.Observation> client : this.streamClients) {
			client.onNext(currentObservation);
		}
	}

	/*
	 * service IoTProtocol { rpc SwitchOff(empty) returns (empty) {} }
	 * 
	 * @see
	 * io.dama.vs.iotprotocol.IoTProtocolGrpc.IoTProtocolImplBase#switchOff(io.dama.
	 * vs.iotprotocol.Iotprotocol.empty, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void switchOff(Iotprotocol.empty in, StreamObserver<Iotprotocol.empty> out) {
		this.sensor.stopSensor();
		List<StreamObserver<Iotprotocol.Observation>> clientsCopy = new ArrayList<>(this.streamClients);
		this.streamClients.clear();
		for (StreamObserver<Iotprotocol.Observation> client : clientsCopy) {
			client.onCompleted();
		}
		out.onNext(emptyResponse);
		out.onCompleted();
	}

	/*
	 * service IoTProtocol { rpc GetObservation(empty) returns (Observation) {} }
	 * 
	 * @see
	 * io.dama.vs.iotprotocol.IoTProtocolGrpc.IoTProtocolImplBase#getObservation(io.
	 * dama.vs.iotprotocol.Iotprotocol.empty, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getObservation(Iotprotocol.empty in, StreamObserver<Iotprotocol.Observation> out) {
		Iotprotocol.Observation value = Iotprotocol.Observation//
				.newBuilder()//
				.setValue(sensor.getValue()).build();
		out.onNext(value);
		out.onCompleted();
	}

	/*
	 * service IoTProtocol { rpc StreamObservations(empty) returns (stream
	 * Observation) {} }
	 * 
	 * @see
	 * io.dama.vs.iotprotocol.IoTProtocolGrpc.IoTProtocolImplBase#streamObservations
	 * (io.dama.vs.iotprotocol.Iotprotocol.empty, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void streamObservations(Iotprotocol.empty in, StreamObserver<Iotprotocol.Observation> out) {
		streamClients.add(out);
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		// SensorServer als Observer bei Sensor anmelden und Sensor nebenläufig starten
		instance.sensor.register(instance);
		instance.sensor.start();

		// Server Instanz erzeugen und starten, danach auf Ende warten
		Server server = ServerBuilder.forPort(Conf.PORT).addService(instance).build();
		server.start();
		server.awaitTermination();
	}
}
