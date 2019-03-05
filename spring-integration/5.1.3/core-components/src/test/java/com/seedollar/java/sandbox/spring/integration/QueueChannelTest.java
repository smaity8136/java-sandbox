package com.seedollar.java.sandbox.spring.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class QueueChannelTest {

    @Test
    @DisplayName("Queue Channel example")
    public void testQueueChannelCapacity() {
        QueueChannel queueChannel = new QueueChannel(3);

        queueChannel.send(MessageBuilder.withPayload("A").build());
        queueChannel.send(MessageBuilder.withPayload("B").build());
        queueChannel.send(MessageBuilder.withPayload("C").build());

        Assertions.assertEquals(3, queueChannel.getQueueSize());
        // This will block indefinitely because the capacity is full, but will timeout after 1 second
        queueChannel.send(MessageBuilder.withPayload("D").build(), 1000l);

        Message<?> receive1 = queueChannel.receive();
        Assertions.assertEquals("A", receive1.getPayload());
        Assertions.assertEquals(2, queueChannel.getQueueSize());
    }
}
