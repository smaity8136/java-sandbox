package com.seedollar.java.sandbox.spring.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dispatcher.RoundRobinLoadBalancingStrategy;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageDeliveryException;

import java.util.stream.IntStream;

@Slf4j
public class DirectChannelTest {

    @Test
    @DisplayName("DirectChannel example")
    public void testDirectChannel() {
        DirectChannel directChannel = new DirectChannel();


        directChannel.subscribe(message -> {
            log.info("Handling message: {}", message.getPayload());
            Assertions.assertTrue(message.getPayload() instanceof String);
        });

        directChannel.send(MessageBuilder.withPayload("Test direct channel message").build());
    }

    @Test
    @DisplayName("DirectChannel LoadBalanceStrategy example")
    public void testDirectChannelLoadBalanceStrategy() {
        // Use round-robin
        DirectChannel loadBalanceDirectChannel = new DirectChannel(new RoundRobinLoadBalancingStrategy());

        // Register 3 message handlers, to illustrate round-robin strategy
        loadBalanceDirectChannel.subscribe(message -> log.info("Message handler 1: {}", message.getPayload()));
        loadBalanceDirectChannel.subscribe(message -> log.info("Message handler 2: {}", message.getPayload()));
        loadBalanceDirectChannel.subscribe(message -> log.info("Message handler 3: {}", message.getPayload()));

        // Now send messages and confirm each handler is consuming messages in a round-robin fashion.
        IntStream.range(0, 10).forEach(val -> loadBalanceDirectChannel.send(MessageBuilder.withPayload(val).build()));
    }

    @Test
    @DisplayName("Datatype Channel example")
    public void testDataTypeChannel() {
        DirectChannel dataTypeChannel = new DirectChannel();

        // Set configure this channel to only accept Float and Double data type message payloads. Anything else would be ignored.
        dataTypeChannel.setDatatypes(Float.class, Double.class);

        dataTypeChannel.subscribe(message -> log.info("Datatype channel accepting message of payload type: {}", message.getPayload().getClass().getSimpleName()));

        Assertions.assertTrue(dataTypeChannel.send(MessageBuilder.withPayload(Float.valueOf(52.49F)).build()));
        Assertions.assertThrows(MessageDeliveryException.class, () -> dataTypeChannel.send(MessageBuilder.withPayload(Integer.valueOf(263)).build()));
        Assertions.assertTrue(dataTypeChannel.send(MessageBuilder.withPayload(Double.valueOf(97.5d)).build()));
        Assertions.assertThrows(MessageDeliveryException.class, () -> dataTypeChannel.send(MessageBuilder.withPayload("Alan").build()));
    }

}
