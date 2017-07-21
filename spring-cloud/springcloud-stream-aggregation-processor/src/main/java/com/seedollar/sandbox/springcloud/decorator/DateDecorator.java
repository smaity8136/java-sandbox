package com.seedollar.sandbox.springcloud.decorator;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import com.seedollar.sandbox.springcloud.aggregation.processor.DateProcessor;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by seedollar on 7/19/17.
 */
@EnableBinding(DateProcessor.class)
public class DateDecorator {

    @ServiceActivator(inputChannel = DateProcessor.INPUT, outputChannel = DateProcessor.OUTPUT)
    public String decorateDate(String timeInMilliseconds) {
        System.out.println("Aggregation Processor -><-");
        Date incomingDate = new Date(Long.parseLong(timeInMilliseconds));
        Calendar decoratedCalendar = Calendar.getInstance();
        decoratedCalendar.setTime(incomingDate);
        return String.format("YEAR = %d, MONTH = %d, DAY = %d, HOUR = %d, MINUTE = %d",
                decoratedCalendar.get(Calendar.YEAR),
                decoratedCalendar.get(Calendar.MONTH),
                decoratedCalendar.get(Calendar.DAY_OF_MONTH),
                decoratedCalendar.get(Calendar.HOUR),
                decoratedCalendar.get(Calendar.MINUTE)
        );
    }
}
