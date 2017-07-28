package com.seedollar.sandbox.springcloud.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/25/17.
 */
public interface VegetableSink {

    final String INPUT = "vegetableChannel";

    @Input(VegetableSink.INPUT)
    SubscribableChannel vegetableInput();


}
