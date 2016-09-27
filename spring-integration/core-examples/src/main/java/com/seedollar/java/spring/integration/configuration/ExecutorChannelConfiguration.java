package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Canon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by seedollar on 9/27/16.
 */
@Configuration
@EnableIntegration
public class ExecutorChannelConfiguration {

    @Bean
    public ExecutorChannel executorChannel() {
        ExecutorChannel executorChannel = new ExecutorChannel(threadPoolTaskExecutor());
        executorChannel.setDatatypes(Canon.class);

        // Add 3 subscribers
        executorChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(String.format("Thread: %s - Task Subscriber 1 = %s", Thread.currentThread().getId(), ((Canon) message.getPayload()).fire()));
            }
        });
        executorChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(String.format("Thread: %s - Task Subscriber 2 = %s", Thread.currentThread().getId(), ((Canon) message.getPayload()).fire()));
                System.out.println("Task Subscriber 2 = " + ((Canon) message.getPayload()).fire());
            }
        });
        executorChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(String.format("Thread: %s - Task Subscriber 3 = %s", Thread.currentThread().getId(), ((Canon) message.getPayload()).fire()));
            }
        });

        return executorChannel;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(5);
        return threadPoolTaskExecutor;
    }
}
