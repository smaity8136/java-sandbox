package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Tank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * Created by seedollar on 9/27/16.
 */
@Configuration
@EnableIntegration
public class QueueChannelConfiguration {

    @Bean(name = "queueChannelMessagingTemplate")
    public MessagingTemplate queueChannelMessagingTemplate() {
        MessagingTemplate messagingTemplate = new MessagingTemplate(queueChannel());
        return messagingTemplate;
    }

    @Bean
    public QueueChannel queueChannel() {
        QueueChannel queueChannel = new QueueChannel(5); // Make sure you set the capacity else the queue will unbounded, and cause result in OutOfMemoryErrors
        queueChannel.setDatatypes(Tank.class);
        queueChannel.addInterceptor(queueChannelInteceptor());
        return queueChannel;
    }

    @Bean
    public ChannelInterceptor queueChannelInteceptor() {
        return new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println(String.format("About to send tank with country: %s", ((Tank) message.getPayload()).getCountry()));
                return message;
            }

            @Override
            public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

            }

            @Override
            public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {

            }

            @Override
            public boolean preReceive(MessageChannel channel) {
                return true; // Must return true
            }

            @Override
            public Message<?> postReceive(Message<?> message, MessageChannel channel) {
                return message; // You must return something, else the channel breaks.
            }

            @Override
            public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {

            }
        };
    }
}
