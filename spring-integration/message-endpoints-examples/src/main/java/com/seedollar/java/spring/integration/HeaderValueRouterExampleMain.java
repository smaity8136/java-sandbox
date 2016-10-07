package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.HeaderValueRouterConfiguration;
import com.seedollar.java.spring.integration.domain.Audi;
import com.seedollar.java.spring.integration.domain.BMW;
import com.seedollar.java.spring.integration.domain.Toyota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by seedollar on 10/6/16.
 */
public class HeaderValueRouterExampleMain implements CommandLineRunner {

    @Autowired
    QueueChannel bmwCarChannel;

    @Autowired
    QueueChannel audiCarChannel;

    @Autowired
    QueueChannel toyotaCarChannel;

    @Autowired
    QueueChannel unknownCarChannel;

    @Autowired
    MessagingTemplate headerValueRouterMessagingTemplateInvoker;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HeaderValueRouterExampleMain.class, HeaderValueRouterConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        BMW bmw = new BMW();
        Audi audi = new Audi();
        Toyota toyota = new Toyota();

        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", bmw.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", bmw.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", "Honda").build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", toyota.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", "Renault").build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", audi.getMake()).build());
        headerValueRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload("car").setHeader("carMake", bmw.getMake()).build());

        System.out.println("bmwCarChannel size = " + bmwCarChannel.getQueueSize());
        System.out.println("audiCarChannel size = " + audiCarChannel.getQueueSize());
        System.out.println("toyotaCarChannel size = " + toyotaCarChannel.getQueueSize());
        System.out.println("unknownCarChannel size = " + unknownCarChannel.getQueueSize());

    }
}
