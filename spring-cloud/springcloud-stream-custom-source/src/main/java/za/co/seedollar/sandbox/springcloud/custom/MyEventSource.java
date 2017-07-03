package za.co.seedollar.sandbox.springcloud.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 6/29/17.
 */

public interface MyEventSource {

    String OUTPUT = "myEventSource";

    @Output(MyEventSource.OUTPUT)
    MessageChannel eventSource();
}
