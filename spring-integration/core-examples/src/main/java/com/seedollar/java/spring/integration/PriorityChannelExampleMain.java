package com.seedollar.java.spring.integration;

import com.google.common.collect.Lists;
import com.seedollar.java.spring.integration.configuration.PriorityChannelConfiguration;
import com.seedollar.java.spring.integration.domain.Car;
import com.seedollar.java.spring.integration.domain.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.support.MutableMessage;
import org.springframework.messaging.Message;

import java.util.ArrayList;

/**
 * Created by seedollar on 9/28/16.
 *
 * The priority Channel will use the comparator to decide which messages will get consumed in order
 * of the Car ranking.
 */
public class PriorityChannelExampleMain implements CommandLineRunner {

    @Autowired
    PriorityChannel priorityChannel;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PriorityChannelExampleMain.class, PriorityChannelConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Send 5 messages of Cars to the priority Channel
        ArrayList<MutableMessage<Car>> carMessages = Lists.newArrayList(
                new MutableMessage<Car>(new Car("USA", 4)),
                new MutableMessage<Car>(new Car("ZA", 2)),
                new MutableMessage<Car>(new Car("Canada", 10)),
                new MutableMessage<Car>(new Car("Austria", 7)),
                new MutableMessage<Car>(new Car("China", 1))
        );

        carMessages.stream().forEach(priorityChannel::send);

        Thread.sleep(2000);

        // Now we consume each message
        for (int x = 0; x < carMessages.size(); x++) {
            Message<?> receive = priorityChannel.receive();
            Car receivedCar = (Car) receive.getPayload();
            System.out.println(String.format("priorityChannel size: %s | car ranking received: %s", priorityChannel.getQueueSize(), receivedCar.getRanking()));
        }

        System.exit(0);

    }
}
