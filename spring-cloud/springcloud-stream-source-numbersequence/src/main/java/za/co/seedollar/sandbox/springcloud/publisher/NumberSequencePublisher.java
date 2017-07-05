package za.co.seedollar.sandbox.springcloud.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import za.co.seedollar.sandbox.springcloud.custom.NumberSequenceSource;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by seedollar on 7/4/17.
 */
@EnableScheduling
@EnableBinding(NumberSequenceSource.class)
public class NumberSequencePublisher {

    @Autowired
    NumberSequenceSource numberSequenceSource;

    @Scheduled(fixedRate = 10000)
    public void publishNumberSequence() {
        // We generate a random sequence of numbers starting from 1 up to 100, and always ensure a minimum range of 50
        IntStream.range(1, new Random().nextInt(100) + 50).forEach(num -> numberSequenceSource.numberSequence().send(MessageBuilder.withPayload(Integer.toString(num)).build()));
    }
}
