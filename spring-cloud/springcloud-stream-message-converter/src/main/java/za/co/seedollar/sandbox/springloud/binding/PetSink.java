package za.co.seedollar.sandbox.springloud.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/5/17.
 */
public interface PetSink {

    String INPUT = "petChannel";

    @Input(PetSink.INPUT)
    SubscribableChannel pets();

}
