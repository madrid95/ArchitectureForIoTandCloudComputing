package io.dama.vs.sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sensor extends Thread {
	private Random rand = new Random();
	private float value;
	private volatile boolean stopped = false;
	private List<SensorObserver> observers = new ArrayList<>();

	public Sensor(float value) {
		this.value = value;
	}

	public synchronized void register(SensorObserver observer) {
		observers.add(observer);
	}

	public synchronized float getValue() {
		return this.value;
	}

	private synchronized void incValue() {
		this.value += this.value / 10.0;
		for (SensorObserver o : observers) {
			o.notify(this.value);
		}
	}

	private synchronized void decValue() {
		this.value -= this.value / 10.0;
		for (SensorObserver o : observers) {
			o.notify(this.value);
		}
	}

	public void stopSensor() {
		this.stopped = true;
		this.interrupt();
	}

	@Override
	public void run() {
		while (!stopped) {
			try {
				if (rand.nextBoolean()) {
					incValue(); // value um 10% erhöhen
				} else {
					decValue(); // value um 10% verringern
				}
				// warte zufällig lange < 1000 ms
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {
				// Warten vorzeitig abgebrochen
			}
		}
	}

}
