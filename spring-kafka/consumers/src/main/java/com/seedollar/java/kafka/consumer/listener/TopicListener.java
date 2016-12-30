package com.seedollar.java.kafka.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by seedollar on 12/30/16.
 */

public class TopicListener {

    private CountDownLatch latch = new CountDownLatch(100);

    @KafkaListener(topics = "test")
    public void onMessage(String data) {
        System.out.println("data consumed = " + data);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
