package za.co.seedollar.sandbox.springcloud.component;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by seedollar on 6/29/17.
 */
@EnableBinding(Source.class)
public class LocalDateTimeGenerator {

    @InboundChannelAdapter(value = Source.OUTPUT)
    public String generateLocalDateTime() {
        return new Date().toString();
    }
}
