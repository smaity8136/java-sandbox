package com.seedollar.java.spring.integration.configuration;

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
 * Created by seedollar on 10/4/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration.endpoint")
public class TransformerMessageEndpointConfiguration {

    @Bean
    public MessagingTemplate transformerMessagingTemplateInvoker() {
        return new MessagingTemplate(beforeTransformerChannel());
    }

    @Bean
    public MessageChannel beforeTransformerChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel afterTransformerChannel() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.subscribe(printTransformedHandler());
        return directChannel;
    }

    @Bean
    public MessageHandler printTransformedHandler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("transformed message = " + message.getPayload());
            }
        };
    }
}
