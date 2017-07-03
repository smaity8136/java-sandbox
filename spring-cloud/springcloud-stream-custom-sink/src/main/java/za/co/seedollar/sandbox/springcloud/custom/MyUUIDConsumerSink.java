package za.co.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/3/17.
 */
public interface MyUUIDConsumerSink {

    String INPUT = "uuidChannel";

    @Input(MyUUIDConsumerSink.INPUT)
    SubscribableChannel uuidChannel();
}
