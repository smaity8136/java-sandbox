package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.TransformerMessageEndpointConfiguration;
import com.seedollar.java.spring.integration.domain.Moth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;

/**
 * Created by seedollar on 10/4/16.
 */
public class TransformerMessageEndpointMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate transformerMessagingTemplateInvoker;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TransformerMessageEndpointMain.class, TransformerMessageEndpointConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        transformerMessagingTemplateInvoker.send(MessageBuilder.withPayload(new Moth("GREY", 10)).build());
    }
}
