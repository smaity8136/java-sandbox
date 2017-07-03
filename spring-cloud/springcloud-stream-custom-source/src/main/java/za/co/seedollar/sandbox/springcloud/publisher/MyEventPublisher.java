package za.co.seedollar.sandbox.springcloud.publisher;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import za.co.seedollar.sandbox.springcloud.custom.MyEventSource;

/**
 * Created by seedollar on 6/29/17.
 */
@EnableBinding(MyEventSource.class)
public class MyEventPublisher {

    @InboundChannelAdapter(value = MyEventPublisher.OUTPUT)
}
