package com.seedollar.java.sandbox.spring.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.support.MessageBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorChannelTest {

    @Test
    @DisplayName("Executor Channel example")
    public void testExecutorChannel() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        ExecutorChannel executorChannel = new ExecutorChannel(fixedThreadPool);
        executorChannel.subscribe(message -> log.info("Payload {} consumed on thread: {}", message.getPayload(), Thread.currentThread().getName()));
        executorChannel.send(MessageBuilder.withPayload("A").build());
        executorChannel.send(MessageBuilder.withPayload("B").build());
        executorChannel.send(MessageBuilder.withPayload("C").build());
        executorChannel.send(MessageBuilder.withPayload("D").build());
        Thread.sleep(1000);
    }
}
