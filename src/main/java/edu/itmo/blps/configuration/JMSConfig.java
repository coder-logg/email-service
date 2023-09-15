package edu.itmo.blps.configuration;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.*;
import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.*;

@Configuration
@EnableJms
public class JMSConfig {

	@Bean
	public ConnectionFactory jmsConnectionFactory() throws JMSException {
		RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		return connectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory jmsConnectionFactory, MessageConverter converter) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(jmsConnectionFactory);
		factory.setDestinationResolver(new DestinationResolver() {
			@NotNull
			@Override
			public Destination resolveDestinationName(Session session, @NotNull String destinationName, boolean pubSubDomain) {
				RMQDestination jmsDestination = new RMQDestination();
				jmsDestination.setDestinationName(destinationName);
				jmsDestination.setAmqpQueueName(destinationName);
				jmsDestination.setAmqp(true);
				return jmsDestination;
			}
		});
		factory.setAutoStartup(true);
		return factory;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
//		converter.setTypeIdPropertyName("_type");
		return converter;
	}
//
//	@Bean
//	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination jmsDestination) {
//		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//		jmsTemplate.setDefaultDestination(jmsDestination);
//		jmsTemplate.setReceiveTimeout(5000);
//		return jmsTemplate;
//	}
}
