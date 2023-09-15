package edu.itmo.blps.configuration;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@EnableIntegration
@Configuration
public class MqttClientConfiguration {

//    @Bean
//    public MessageChannel inputChannel() {
//        return new DirectChannel();
//    }
////
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setServerURIs(new String[]{"tcp://localhost:1883"});
//        options.setUserName("guest");
//        options.setPassword("guest".toCharArray());
//        factory.setConnectionOptions(options);
//        return factory;
//    }
//
//    @Bean
//    public Binding mqttBinding() {
//        return new Binding("mailbox", Binding.DestinationType.QUEUE, "mailbox-exchange", "mailbox", null);
//    }

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactory factory = new ConnectionFactory();
//        try (Connection connection = factory.newConnection()) {
//            connection.createChannel().queueBind("mailbox", "jms-exchange");
//        } catch (TimeoutException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return factory;
//    }



}
