package com.seedollar.java.kafka.consumer;

import com.seedollar.java.kafka.consumer.listener.TopicListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by seedollar on 12/30/16.
 */
@SpringBootApplication
public class SpringKafkaConsumerMain implements CommandLineRunner {

    @Autowired
    TopicListener topicListener;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringKafkaConsumerMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(10000);
        System.out.println("topiclistener countdown latch = " + topicListener.getLatch().getCount());
        System.exit(0);
    }
}
