package de.hs_mannheim.ffi.vs.actor;

public final class MQTTConnect {
	private final String broker;

	public MQTTConnect(String broker) {
		this.broker = broker;
	}
	public String getBroker() {
		return this.broker;
	}
}
