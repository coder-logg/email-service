package edu.itmo.blps.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DestinationResolver;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
@EnableJms
public class JMSConfig {
	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;

	@Bean
	public ConnectionFactory jmsConnectionFactory() throws JMSException {
		RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setChannelsQos(0);
		return connectionFactory;
	}

	@Bean
	public DestinationResolver destinationResolver(){
		return (session, destinationName, pubSubDomain) -> {
			RMQDestination jmsDestination = new RMQDestination();
			jmsDestination.setDestinationName(destinationName);
			jmsDestination.setAmqpRoutingKey(routingKey);
//			jmsDestination.setAmqpExchangeName(exchange);
			jmsDestination.setAmqpQueueName("mqtt-queue");
			jmsDestination.setAmqp(true);
			return jmsDestination;
		};
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory jmsConnectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(jmsConnectionFactory);
//		factory.setMessageConverter(new MappingJackson2MessageConverter());
		factory.setDestinationResolver(destinationResolver());
		factory.setAutoStartup(true);
		return factory;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
