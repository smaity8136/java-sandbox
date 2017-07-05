package za.co.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/4/17.
 */
public interface NumberSequenceSink {

    String INPUT = "numberSequenceChannel";

    @Input(NumberSequenceSink.INPUT)
    SubscribableChannel numberSequenceChannel();
}
