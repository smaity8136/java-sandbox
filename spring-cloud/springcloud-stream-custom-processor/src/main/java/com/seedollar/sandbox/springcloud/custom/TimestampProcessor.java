package com.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/3/17.
 */
public interface TimestampProcessor {

    String INPUT = "timestampChannel";
    String OUTPUT = "timestampTransformedChannel";

    @Input(TimestampProcessor.INPUT)
    SubscribableChannel timestampInput();

    @Output(TimestampProcessor.OUTPUT)
    MessageChannel timestampTransformedOutput();
}
