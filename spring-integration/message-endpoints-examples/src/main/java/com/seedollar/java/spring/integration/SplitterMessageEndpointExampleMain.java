package com.seedollar.java.spring.integration;

import com.google.common.collect.Lists;
import com.seedollar.java.spring.integration.configuration.SplitterMessageEndpointConfiguration;
import com.seedollar.java.spring.integration.domain.Audi;
import com.seedollar.java.spring.integration.domain.BMW;
import com.seedollar.java.spring.integration.domain.Toyota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by seedollar on 10/7/16.
 */
public class SplitterMessageEndpointExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate splitterMessagingTemplateInvoker;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SplitterMessageEndpointExampleMain.class, SplitterMessageEndpointConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        splitterMessagingTemplateInvoker.send(MessageBuilder.withPayload(Lists.newArrayList(new BMW(), new Audi(), new Toyota(), new Audi(), new BMW())).build());
    }
}
