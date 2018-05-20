package io.dama.vs.sensor;

import io.dama.vs.hase.Hase;

public interface SensorObserver {
	public void notify(Hase.Telegram observation);
}
