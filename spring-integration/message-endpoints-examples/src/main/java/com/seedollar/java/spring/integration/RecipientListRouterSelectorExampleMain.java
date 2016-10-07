package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.RecipientListRouterConfiguration;
import com.seedollar.java.spring.integration.configuration.RecipientListRouterSelectorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by seedollar on 10/7/16.
 *
 * Shows how you can use a populated Recipient object with a selector to filter the messages.
 */
public class RecipientListRouterSelectorExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate recipientListRouterSelectorMessagingTemplateInvoker;

    @Autowired
    QueueChannel lowIncomeChannel;

    @Autowired
    QueueChannel middleClassChannel;

    @Autowired
    QueueChannel wealthChannel;

    @Autowired
    QueueChannel governmentInterventionChannel;


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RecipientListRouterSelectorExampleMain.class, RecipientListRouterSelectorConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer1").setHeader("platinum", "7500000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer2").setHeader("platinum", "7500000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer3").setHeader("gold", "5000000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer4").setHeader("refugee", "4862").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer5").setHeader("platinum", "7500000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer6").setHeader("immigrant", "97497").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer7").setHeader("gold", "5000000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer8").setHeader("gold", "5000000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer9").setHeader("platinum", "7500000").build());
        recipientListRouterSelectorMessagingTemplateInvoker.send(MessageBuilder.withPayload("customer10").setHeader("silver", "200000").build());

        System.out.println("lowIncomeChannel size = " + lowIncomeChannel.getQueueSize());
        System.out.println("middleClassChannel size = " + middleClassChannel.getQueueSize());
        System.out.println("wealthChannel size = " + wealthChannel.getQueueSize());
        System.out.println("governmentInterventionChannel size = " + governmentInterventionChannel.getQueueSize());
    }
}
