package edu.itmo.blps.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.blps.messaging.dto.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Receiver {

	@Autowired
	private ObjectMapper mapper;

	@JmsListener(destination = "mqtt-queue")
	public EmailMessage receiveMessage(MqttMessage message) {
		log.info("Received message: <{}>", new String(message.getPayload()));
		try {
			return mapper.readValue(message.getPayload(), EmailMessage.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}