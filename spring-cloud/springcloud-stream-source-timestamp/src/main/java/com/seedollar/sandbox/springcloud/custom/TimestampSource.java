package com.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/3/17.
 */
public interface TimestampSource {

    String OUTPUT = "timestampChannel";

    @Output(TimestampSource.OUTPUT)
    MessageChannel timestampOutput();
}
