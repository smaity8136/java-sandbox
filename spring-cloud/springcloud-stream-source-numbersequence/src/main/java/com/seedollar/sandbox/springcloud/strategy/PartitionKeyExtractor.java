package com.seedollar.sandbox.springcloud.strategy;

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;

/**
 * Created by seedollar on 7/5/17.
 */
public class PartitionKeyExtractor implements PartitionKeyExtractorStrategy {

    @Override
    public String extractKey(Message<?> message) {
        return message.getPayload().toString();
    }
}
