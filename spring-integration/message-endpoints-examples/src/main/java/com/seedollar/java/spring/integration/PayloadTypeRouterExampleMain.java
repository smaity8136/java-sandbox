package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.PayloadTypeRouterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;

import java.util.UUID;

/**
 * Created by seedollar on 10/6/16.
 */
public class PayloadTypeRouterExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate payloadTypeRouterMessagingTemplateInvoker;

    @Autowired
    @Qualifier("stringTypeChannel")
    QueueChannel stringTypeChannel;

    @Autowired
    @Qualifier("integerTypeChannel")
    QueueChannel integerTypeChannel;

    @Autowired
    @Qualifier("booleanTypeChannel")
    QueueChannel booleanTypeChannel;

    @Autowired
    @Qualifier("unknownTypeChannel")
    QueueChannel unknownTypeChannel;

    @Autowired
    @Qualifier("errorTypeChannel")
    QueueChannel errorTypeChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PayloadTypeRouterExampleMain.class, PayloadTypeRouterConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("String payload1").build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(Boolean.FALSE).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Integer(8974)).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Integer(5972)).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Exception("mock failure")).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("String payload2").build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(Boolean.TRUE).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Float(973.46)).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Integer(697)).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("String payload3").build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new UUID(9, 45)).build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("String payload4").build());
        payloadTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("String payload5").build());

        Thread.sleep(2000);

        System.out.println("stringTypeChannel size = " + stringTypeChannel.getQueueSize());
        System.out.println("integerTypeChannel size = " + integerTypeChannel.getQueueSize());
        System.out.println("booleanTypeChannel size = " + booleanTypeChannel.getQueueSize());
        System.out.println("unknownTypeChannel size = " + unknownTypeChannel.getQueueSize());
        System.out.println("errorTypeChannel size = " + errorTypeChannel.getQueueSize());

        System.exit(0);

    }
}
