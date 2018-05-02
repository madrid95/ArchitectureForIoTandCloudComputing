package de.hs_mannheim.ffi.vs.actor;

/*
 *  Immutual Data-Container with Builder
 *  Usage:
 *  	MQTTMessage msg = MQTTMessage.newBuilder() 
				.setTopic("t1") 
				.setPayload("Hallo") 
				.build();
 */
public class MQTTMessage {

	// Properties
	private final String topic;
	private final Object payload;

	// Constructor
	public MQTTMessage(String topic, Object payload) {
		this.topic = topic;
		this.payload = payload;
	}

	// Getters for properties
	public String getTopic() {
		return this.topic;
	}

	public Object getPayload() {
		return this.payload;
	}

	// Builder as static inner class
	public static class MQTTMessageBuilder {
		// Properties
		private String topic;
		private Object payload;

		// fluid Setters for Builder
		public MQTTMessageBuilder setTopic(String topic) {
			this.topic = topic;
			return this;
		}

		public MQTTMessageBuilder setPayload(Object payload) {
			this.payload = payload;
			return this;
		}

		// make immutual
		public MQTTMessage build() {
			return new MQTTMessage(this.topic, this.payload);
		}
	}
	public static MQTTMessageBuilder newBuilder() {
		return new MQTTMessageBuilder();
	}
}
