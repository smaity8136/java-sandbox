package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Canon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by seedollar on 9/27/16.
 */
@Configuration
@EnableIntegration
public class PublishSubscribeChannelConfiguration {

    @Bean
    public PublishSubscribeChannel publishSubscribeChannel() {
        PublishSubscribeChannel publishSubscribeChannel = new PublishSubscribeChannel();
        publishSubscribeChannel.setDatatypes(Canon.class);

        // Setup 2 subscribers, to show that the message gets broadcast to all subscribers
        publishSubscribeChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("Subscriber 1 = " + ((Canon)message.getPayload()).fire());
            }
        });

        publishSubscribeChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("Subscriber 2 = " + ((Canon)message.getPayload()).fire());
            }
        });

        return publishSubscribeChannel;
    }
}
