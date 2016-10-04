package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 9/28/16.
 */
@Configuration
@EnableIntegration
public class EventDrivenConsumerConfiguration {

    @Bean
    public EventDrivenConsumer eventDrivenConsumer() {
        EventDrivenConsumer eventDrivenConsumer = new EventDrivenConsumer(subscribableChannel(), messageHandler());
        return eventDrivenConsumer;
    }

    @Bean
    public SubscribableChannel subscribableChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageHandler messageHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("message = " + message);
            }
        };
    }
}
