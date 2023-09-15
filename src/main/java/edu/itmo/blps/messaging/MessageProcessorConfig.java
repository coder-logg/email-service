package edu.itmo.blps.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

@Configuration
@Slf4j
public class MessageProcessorConfig {

//    @Autowired
//    private MqttPahoClientFactory clientFactory;
//
//    @Bean
//    public MessageProducer inbound(@Qualifier("inputChannel") MessageChannel mqttInputChannel) {
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
//                "mail-sender", clientFactory, "mailbox");
//        adapter.setCompletionTimeout(5000);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        adapter.setOutputChannel(mqttInputChannel);
//        return adapter;
//    }

//    private final MessageValidator messageValidator = new MessageValidator();

//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler handler(){
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//
//            }
//        }
//
//    }


}
