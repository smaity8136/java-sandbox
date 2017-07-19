package za.co.seedollar.sandbox.springcloud.aggregation.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import za.co.seedollar.sandbox.springcloud.aggregation.source.DateSource;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by seedollar on 7/19/17.
 */
@EnableBinding(DateSource.class)
public class DateGenerator {

    @InboundChannelAdapter(channel = DateSource.OUTPUT)
    public String generateDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(
                new Random().nextInt(2025),
                new Random().nextInt(11),
                new Random().nextInt(28),
                new Random().nextInt(23),
                new Random().nextInt(59)
        );
        return String.valueOf(instance.getTimeInMillis());
    }
}
