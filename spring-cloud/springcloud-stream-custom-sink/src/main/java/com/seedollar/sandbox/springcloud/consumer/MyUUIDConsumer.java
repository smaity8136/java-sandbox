package com.seedollar.sandbox.springcloud.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.seedollar.sandbox.springcloud.custom.MyUUIDConsumerSink;

/**
 * Created by seedollar on 7/3/17.
 */
@EnableBinding(MyUUIDConsumerSink.class)
public class MyUUIDConsumer {

    @StreamListener(MyUUIDConsumerSink.INPUT)
    public void consumeUUID(String uuid) {
        System.out.println("Consuming UUID: " + uuid);
    }
}
