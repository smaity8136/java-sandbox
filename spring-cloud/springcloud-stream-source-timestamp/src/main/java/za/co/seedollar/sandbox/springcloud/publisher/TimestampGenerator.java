package za.co.seedollar.sandbox.springcloud.publisher;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.stereotype.Component;
import za.co.seedollar.sandbox.springcloud.custom.TimestampSource;

import java.util.Date;


/**
 * Created by seedollar on 7/3/17.
 */
@Component
@EnableBinding(TimestampSource.class)
public class TimestampGenerator {

    @InboundChannelAdapter(value = TimestampSource.OUTPUT)
    public Long generateTimestamp() {
        return new Date().getTime();
    }
}
