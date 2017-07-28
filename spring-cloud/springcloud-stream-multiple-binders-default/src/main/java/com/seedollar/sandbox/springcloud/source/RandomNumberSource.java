package com.seedollar.sandbox.springcloud.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/25/17.
 */
public interface RandomNumberSource {

    final String OUTPUT = "randomNumChannel";

    @Output(RandomNumberSource.OUTPUT)
    MessageChannel randomNumOutput();
}
