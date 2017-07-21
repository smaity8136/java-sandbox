package com.seedollar.sandbox.springcloud.publisher;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.MessagingException;
import com.seedollar.sandbox.springcloud.binding.OddNumberSource;

import java.util.Random;

/**
 * Created by seedollar on 7/5/17.
 */
@EnableBinding(OddNumberSource.class)
public class OddNumberPublisher {

    @InboundChannelAdapter(value = OddNumberSource.OUTPUT)
    public Integer publishOddNumbers() {
        int nextInt = new Random().nextInt(200);
        if (nextInt % 2 == 0) {
            throw new MessagingException(String.format("We cannot process this even number: %d", nextInt));
        } else {
            return nextInt;
        }
    }
}
