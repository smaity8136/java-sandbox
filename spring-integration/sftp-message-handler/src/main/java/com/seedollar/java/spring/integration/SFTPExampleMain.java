package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.SFTPConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;

/**
 * Created by seedollar on 10/6/16.
 */
public class SFTPExampleMain implements CommandLineRunner {

    @Autowired
    MessagingTemplate sftpMessagingTemplateInvoker;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SFTPExampleMain.class, SFTPConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        sftpMessagingTemplateInvoker.send(MessageBuilder.withPayload(new byte[] {1,2,3,4,5,6,7,8,9,10}).setHeader("fileType", "pdf").build());
        sftpMessagingTemplateInvoker.send(MessageBuilder.withPayload(new byte[] {1,2,3,4,5,6,7,8,9,10}).setHeader("fileType", "mp3").build());
        sftpMessagingTemplateInvoker.send(MessageBuilder.withPayload(new byte[] {1,2,3,4,5,6,7,8,9,10}).setHeader("fileType", "tiff").build());
        sftpMessagingTemplateInvoker.send(MessageBuilder.withPayload(new byte[] {1,2,3,4,5,6,7,8,9,10}).setHeader("fileType", "jpg").build());
        Thread.sleep(10000);
    }
}
