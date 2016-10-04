package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.EventDrivenConsumerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 9/28/16.
 */
public class EventDrivenConsumerExampleMain implements CommandLineRunner {

    @Autowired
    EventDrivenConsumer eventDrivenConsumer;

    @Autowired
    SubscribableChannel subscribableChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EventDrivenConsumerExampleMain.class, EventDrivenConsumerConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        subscribableChannel.send(MessageBuilder.withPayload("test event driven consumer1").build());
        subscribableChannel.send(MessageBuilder.withPayload("test event driven consumer2").build());
    }
}
