package com.seedollar.sandbox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import com.seedollar.sandbox.springcloud.aggregation.sink.DateSummarySink;

/**
 * Created by seedollar on 7/21/17.
 */
@SpringBootApplication
@EnableBinding(DateSummarySink.class)
public class SpringCloudStreamAggregationSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamAggregationSinkApplication.class, args);
    }

    @ServiceActivator(inputChannel = DateSummarySink.INPUT)
    public void logDateSummary(String dateSummary) {
        System.out.println("Aggregation Sink -> " + dateSummary);
    }
}
