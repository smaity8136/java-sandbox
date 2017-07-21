package com.seedollar.sandbox.springcloud.aggregation.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/21/17.
 */
public interface DateSummarySink {

    final String INPUT = "dateOverviewChannel";

    @Input(DateSummarySink.INPUT)
    SubscribableChannel dateSummaryInput();
}
