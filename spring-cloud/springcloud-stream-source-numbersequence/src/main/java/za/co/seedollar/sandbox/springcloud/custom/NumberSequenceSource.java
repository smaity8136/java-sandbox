package za.co.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/4/17.
 */
public interface NumberSequenceSource {

    String OUTPUT = "numberSequenceChannel";

    @Output(NumberSequenceSource.OUTPUT)
    MessageChannel numberSequence();
}
