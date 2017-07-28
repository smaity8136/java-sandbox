package com.seedollar.sandbox.springcloud.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/25/17.
 */
public interface FruitSource {

    final String OUTPUT = "fruitChannel";

    @Output(FruitSource.OUTPUT)
    MessageChannel fruitOutput();
}
