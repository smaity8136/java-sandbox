package com.seedollar.sandbox.springcloud.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.seedollar.sandbox.springcloud.custom.NumberSequenceSink;

/**
 * Created by seedollar on 7/4/17.
 */
@EnableBinding(NumberSequenceSink.class)
public class NumberSequenceConsumer {

    @StreamListener(value = NumberSequenceSink.INPUT)
    public void consumeNumberSequence(String number) {
        System.out.println("number = " + number);
    }
}
