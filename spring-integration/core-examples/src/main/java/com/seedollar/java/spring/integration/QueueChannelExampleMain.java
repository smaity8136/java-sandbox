package com.seedollar.java.spring.integration;

import com.google.common.collect.Lists;
import com.seedollar.java.spring.integration.configuration.QueueChannelConfiguration;
import com.seedollar.java.spring.integration.domain.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MutableMessage;
import org.springframework.messaging.Message;

import java.util.ArrayList;

/**
 * Created by seedollar on 9/27/16.
 */
public class QueueChannelExampleMain implements CommandLineRunner {

    @Autowired
    QueueChannel queueChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(QueueChannelExampleMain.class, QueueChannelConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Send 5 messages of Tanks to the queue Channel
        ArrayList<MutableMessage<Tank>> tankMessages = Lists.newArrayList(
                new MutableMessage<Tank>(new Tank("USA")),
                new MutableMessage<Tank>(new Tank("ZA")),
                new MutableMessage<Tank>(new Tank("Canada")),
                new MutableMessage<Tank>(new Tank("Austria")),
                new MutableMessage<Tank>(new Tank("China"))
        );

        // Make sure you only send 5 messages, else the thread blocks.
        tankMessages.stream().forEach(queueChannel::send);

        Thread.sleep(2000);

        // Now we consume each message
        for (int x = 0; x < tankMessages.size(); x++) {
            Message<?> receive = queueChannel.receive();
            Tank receivedTank = (Tank) receive.getPayload();
            System.out.println(String.format("queueChannel size: %s | tank received: %s", queueChannel.getQueueSize(), receivedTank.getCountry()));
        }

        System.exit(0);
    }
}
