package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.FilterMessageEndpointConfiguration;
import com.seedollar.java.spring.integration.domain.Moth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/4/16.
 *
 * This example shows how the Filter MessageEndpoint works.
 */
public class FilterMessageEndpointMain implements CommandLineRunner {

    @Autowired
    private MessagingTemplate filterMessageTemplateInvoker;

    @Autowired
    QueueChannel completeFilterChannel;

    @Autowired
    QueueChannel discardFilterChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FilterMessageEndpointMain.class, FilterMessageEndpointConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        // We send through 10 Moths, some have the colour BLACK and a wingspan > 7. The rest
        // will get discarded into the discardFilterChannel.
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("RED", 12)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("YELLOW", 5)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLACK", 20)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLACK", 11)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("GREEN", 15)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLACK", 13)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLUE", 6)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLACK", 15)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("YELLOW", 7)).build());
        filterMessageTemplateInvoker.send(MessageBuilder.withPayload(new Moth("BLACK", 17)).build());

        Thread.sleep(3000);
        System.out.println("completeFilterChannel size = " + completeFilterChannel.getQueueSize());
        System.out.println("discardFilterChannel size = " + discardFilterChannel.getQueueSize());


    }


}
