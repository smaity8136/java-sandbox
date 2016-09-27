package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.PublishSubscribeChannelConfiguration;
import com.seedollar.java.spring.integration.domain.Canon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MutableMessage;

/**
 * Created by seedollar on 9/27/16.
 * <p>
 * Note that the PublishSubscribeChannel is intended for sending only.
 */
public class PublishSubscribeChannelExampleMain implements CommandLineRunner {

    @Autowired
    PublishSubscribeChannel publishSubscribeChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PublishSubscribeChannelExampleMain.class, PublishSubscribeChannelConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        publishSubscribeChannel.send(new MutableMessage<Canon>(new Canon(1400)));
    }
}
