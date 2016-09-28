package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * Created by seedollar on 9/28/16.
 */
@Configuration
public class GlobalChannelInterceptorConfiguration {

    @Bean
    @GlobalChannelInterceptor(patterns = {"counting*", "priorityChannel"})
    public ChannelInterceptor globalAuditChannelInteceptor() {
        return new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println("GLOBAL CHANNEL AUDIT - preSend() " + message);
                return message;
            }

            @Override
            public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
                System.out.println("GLOBAL CHANNEL AUDIT - postSend() " + message);
            }

            @Override
            public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
                System.out.println("GLOBAL CHANNEL AUDIT - afterSendCompletion() " + message);
            }

            @Override
            public boolean preReceive(MessageChannel channel) {
                System.out.println("GLOBAL CHANNEL AUDIT - preReceive()");
                return true;
            }

            @Override
            public Message<?> postReceive(Message<?> message, MessageChannel channel) {
                System.out.println("GLOBAL CHANNEL AUDIT - postReceive()" + message);
                return message;
            }

            @Override
            public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
                System.out.println("GLOBAL CHANNEL AUDIT - afterReceiveCompletion()" + message);
            }
        };
    }
}
