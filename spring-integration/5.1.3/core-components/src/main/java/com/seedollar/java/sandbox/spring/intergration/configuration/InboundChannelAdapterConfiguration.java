package com.seedollar.java.sandbox.spring.intergration.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
@Slf4j
public class InboundChannelAdapterConfiguration {

    @InboundChannelAdapter(channel = "inboundDirectChannel", poller = @Poller(fixedDelay = "1"))
    public Message<?> handleMessage() {
        return MessageBuilder.withPayload(ThreadLocalRandom.current().nextInt()).build();
    }

    @Bean
    public DirectChannel inboundDirectChannel() {
        DirectChannel directChannel = MessageChannels.direct("inboundDirectChannel")
                .get();
        directChannel.subscribe(message -> log.info("Inbound Channel Adapter consuming message with payload: {}", message.getPayload()));
        return directChannel;
    }
}
