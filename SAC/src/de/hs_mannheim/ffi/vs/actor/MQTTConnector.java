package de.hs_mannheim.ffi.vs.actor;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import akka.actor.AbstractLoggingActor;
import akka.actor.AbstractActor.Receive;

public class MQTTConnector extends AbstractLoggingActor {

    private MqttClient client;

	@Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(MQTTConnect.class, (msg) -> {
        			this.connect(msg.getBroker());
                })
                .match(MQTTSubscribe.class, (msg) -> {
                	client.subscribe(msg.getTopic());
                }).build();
    }
	
	public boolean connect(String broker){
		try{
			client = new MqttClient(broker, null);
			client.connect();
			client.setCallback(new MqttCallback() {

				@Override
				public void connectionLost(Throwable e) {
					log().error(e.getMessage());
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken arg0) {
				}

				@Override
				public void messageArrived(String topic, MqttMessage m) throws Exception {
					context().parent().tell(new MQTTMessage(topic, m.getPayload()), getSelf());
				}
			});
			return false;
		}catch(Exception e){
			log().error(e.getMessage());
			return true;
		}
	}
	
	public boolean subscribe(String topic){
	try{
    	client.subscribe(topic);
		return false;
	}catch(Exception e){
		log().error(e.getMessage());
		return true;
	}
	}

}
