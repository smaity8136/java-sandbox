package za.co.seedollar.sandbox.springcloud.component;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.annotation.SubscribeMapping;

/**
 * Created by seedollar on 6/29/17.
 */
@EnableBinding(Sink.class)
public class TimePrinter {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void printTime(String time) {
        System.out.println("time = " + time);
    }
}
