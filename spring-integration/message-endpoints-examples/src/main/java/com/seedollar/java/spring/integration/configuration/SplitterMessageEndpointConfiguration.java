package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by seedollar on 10/7/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration")
public class SplitterMessageEndpointConfiguration {

    @Bean
    public MessagingTemplate splitterMessagingTemplateInvoker() {
        return new MessagingTemplate(startSplitterChannel());
    }

    @Bean
    public MessageChannel startSplitterChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel carProcessingChannel() {
        DirectChannel carProcessingChannel = new DirectChannel();
        carProcessingChannel.subscribe(carProcessingHandler());
        return carProcessingChannel;
    }

    @Bean
    public MessageHandler carProcessingHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                Car payloadCar = (Car) message.getPayload();
                System.out.println("Processing Car make " + payloadCar.getMake());
            }
        };
    }

}
