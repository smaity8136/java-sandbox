package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.ErrorMessageExceptionTypeRouterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;

import java.io.IOException;

/**
 * Created by seedollar on 10/7/16.
 */
public class ErrorMessageExceptionTypeRouterExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate errorMessageExceptionTypeRouterMessagingTemplateInvoker;

    @Autowired
    QueueChannel checkedExceptionChannel;

    @Autowired
    QueueChannel uncheckedExceptionChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ErrorMessageExceptionTypeRouterExampleMain.class, ErrorMessageExceptionTypeRouterConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IllegalArgumentException("illegal1")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IllegalArgumentException("illegal2")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new NullPointerException("null1")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IOException("io1")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IOException("io2")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IOException("io3")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IllegalArgumentException("illegal3")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new NullPointerException("null2")).build());
        errorMessageExceptionTypeRouterMessagingTemplateInvoker.send(MessageBuilder.withPayload(new IllegalArgumentException("illegal4")).build());

        System.out.println("checkedExceptionChannel size = " + checkedExceptionChannel.getQueueSize());
        System.out.println("uncheckedExceptionChannel size = " + uncheckedExceptionChannel.getQueueSize());

    }
}
