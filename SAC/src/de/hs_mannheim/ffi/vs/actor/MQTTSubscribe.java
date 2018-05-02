package de.hs_mannheim.ffi.vs.actor;

public final class MQTTSubscribe {
	private final String topic;

	public MQTTSubscribe(String topic) {
		this.topic = topic;
	}
	public String getTopic() {
		return this.topic;
	}
}
