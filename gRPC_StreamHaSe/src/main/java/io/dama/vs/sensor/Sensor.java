package io.dama.vs.sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.dama.vs.hase.Hase;

public class Sensor extends Thread {
	private Random rand = new Random();

	private final Hase.Sensor sensor = Hase.Sensor.newBuilder().setSensorID(this.rand.nextInt(10000)).build();
	private int continuousNumber = 0;
	private boolean hail = false;
	private int occurrencesPL = this.rand.nextInt(10000);
	private int occurrencesGS = this.rand.nextInt(10000);
	private int occurrencesGR = this.rand.nextInt(10000);
	private boolean microphone = false;
	private int microphoneCapacity = this.rand.nextInt(10000);

	private volatile boolean stopped = false;
	private List<SensorObserver> observers = new ArrayList<>();

	public synchronized void register(SensorObserver observer) {
		observers.add(observer);
	}

	public void stopSensor() {
		this.stopped = true;
		this.interrupt();
	}

	public int getSensorID() {
		return this.sensor.getSensorID();
	}

	public Hase.Sensor getSensor() {
		return this.sensor;
	}

	public Hase.Telegram getObservation() {
		return Hase.Telegram.newBuilder()//
				.setSensor(this.sensor)//
				.setContinuousNumber(this.continuousNumber++)//
				.setHail(this.hail)//
				.setOccurrencesPL(this.occurrencesPL)//
				.setOccurrencesGS(this.occurrencesGS)//
				.setOccurrencesGR(this.occurrencesGR)//
				.setMicrophone(this.microphone)//
				.setMicrophoneCapacity(this.microphoneCapacity)//
				.build();
	}

	@Override
	public void run() {
		while (!stopped) {
			try {
				if (this.rand.nextBoolean()) {
					this.hail = !this.hail;
					for (SensorObserver o : this.observers) {
						o.notify(getObservation());
					}
				}
				// warte zufällig lange < 1000 ms
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				// Warten vorzeitig abgebrochen
			}
		}
	}
}
