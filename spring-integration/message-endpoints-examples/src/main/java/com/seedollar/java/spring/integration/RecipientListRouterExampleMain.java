package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.RecipientListRouterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by seedollar on 10/7/16.
 */
public class RecipientListRouterExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate recipientListRouterMessagingTemplateInvoker;

    @Autowired
    QueueChannel salesChannel;

    @Autowired
    QueueChannel infrastructureChannel;

    @Autowired
    QueueChannel developmentChannel;


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RecipientListRouterExampleMain.class, RecipientListRouterConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        recipientListRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("corporate message1").build());
        recipientListRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("corporate message2").build());
        recipientListRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("corporate message3").build());

        System.out.println("salesChannel size = " + salesChannel.getQueueSize());
        System.out.println("infrastructureChannel size = " + infrastructureChannel.getQueueSize());
        System.out.println("developmentChannel size = " + developmentChannel.getQueueSize());
    }
}
