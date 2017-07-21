package com.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/3/17.
 */
public interface MyUUIDGeneratorSource {

    String OUTPUT = "uuidChannel";

    @Output(MyUUIDGeneratorSource.OUTPUT)
    MessageChannel myUUIDGeneratorOutput();
}
