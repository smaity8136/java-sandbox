package com.seedollar.java.spring.integration;

import com.seedollar.java.spring.integration.configuration.ExecutorChannelConfiguration;
import com.seedollar.java.spring.integration.domain.Canon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.support.MutableMessage;

/**
 * Created by seedollar on 9/27/16.
 * An illustration of the ExecutorChannel, where the messages be send to the subscribers via different threads.
 */
public class ExecutorChannelExampleMain implements CommandLineRunner {

    @Autowired
    ExecutorChannel executorChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ExecutorChannelExampleMain.class, ExecutorChannelConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        executorChannel.send(new MutableMessage<Canon>(new Canon(2783)));
    }
}
