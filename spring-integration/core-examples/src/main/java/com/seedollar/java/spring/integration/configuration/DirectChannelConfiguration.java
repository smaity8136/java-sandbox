package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Car;
import com.seedollar.java.spring.integration.domain.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by seedollar on 9/27/16.
 */
@Configuration
@EnableIntegration
@Import(GlobalChannelInterceptorConfiguration.class)
@ComponentScan(value = {"com.seedollar.java.spring.integration.service.impl"})
public class DirectChannelConfiguration {

    /**
     * This messaging template allows to you send you domain specific POJO to Spring's messaging system explicitly.
     *
     * @return
     */
    @Bean(name = "directChannelMessagingTemplate")
    public MessagingTemplate directChannelMessagingTemplate() {
        MessagingTemplate messagingTemplate = new MessagingTemplate(countingChannel());
        return messagingTemplate;
    }

    @Bean
    public DirectChannel countingChannel() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.setDatatypes(Vehicle.class); // Force the message payload to be of type Vehicle
        directChannel.subscribe(countingMessageHandler());
        return directChannel;
    }

    @Bean
    public MessageHandler countingMessageHandler() {

        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {

                Object payload = message.getPayload();
                if (payload instanceof Car) {
                    System.out.println("message = " + ((Car) payload).getBrand());
                } else {
                    System.out.println("message = " + message.getPayload());
                }
            }
        };
    }
}
