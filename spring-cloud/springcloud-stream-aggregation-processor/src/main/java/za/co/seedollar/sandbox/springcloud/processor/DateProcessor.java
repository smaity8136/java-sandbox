package za.co.seedollar.sandbox.springcloud.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/19/17.
 */
public interface DateProcessor {

    final String INPUT = "dateChannel";
    final String OUTPUT = "dateOverviewChannel";

    @Input(DateProcessor.INPUT)
    SubscribableChannel dateInput();

    @Output(DateProcessor.OUTPUT)
    MessageChannel dateSummaryOutput();
}
