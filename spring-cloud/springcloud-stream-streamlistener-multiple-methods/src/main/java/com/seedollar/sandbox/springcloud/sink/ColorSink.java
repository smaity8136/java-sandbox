package com.seedollar.sandbox.springcloud.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/18/17.
 */
public interface ColorSink {

    final String INPUT = "colorChannel";

    @Input(ColorSink.INPUT)
    SubscribableChannel colorInput();
}
