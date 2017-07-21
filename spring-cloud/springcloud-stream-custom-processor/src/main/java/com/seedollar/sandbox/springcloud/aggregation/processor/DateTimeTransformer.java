package com.seedollar.sandbox.springcloud.aggregation.processor;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import com.seedollar.sandbox.springcloud.custom.TimestampProcessor;

import java.util.Date;

/**
 * Created by seedollar on 7/3/17.
 */
@Component
@EnableBinding(TimestampProcessor.class)
public class DateTimeTransformer {

    @ServiceActivator(inputChannel = TimestampProcessor.INPUT, outputChannel = TimestampProcessor.OUTPUT)
    public String convertToDateFromLong(Long timestamp) {
        return new Date(timestamp).toString();
    }
}