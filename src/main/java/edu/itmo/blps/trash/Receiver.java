package edu.itmo.blps.trash;

import com.rabbitmq.jms.client.message.RMQTextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
@Component
public class Receiver {

  private static final String MQTT_TOPIC = "test-topic";

//  @Autowired
//  private MqttPahoClientFactory mqttClientFactory;

  @JmsListener(destination = "mailbox")
  public void receiveMessage(byte [] message) throws JMSException {
    log.info("Received <{}>", new String(message));
  }
}