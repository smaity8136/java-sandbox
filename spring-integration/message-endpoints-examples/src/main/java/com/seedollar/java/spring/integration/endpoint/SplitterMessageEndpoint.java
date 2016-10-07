package com.seedollar.java.spring.integration.endpoint;

import com.seedollar.java.spring.integration.domain.Car;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seedollar on 10/7/16.
 */
@Component
public class SplitterMessageEndpoint {

    @Splitter(inputChannel = "startSplitterChannel", outputChannel = "carProcessingChannel")
    public List<Car> splitCars(Message<?> message) {
        return (List<Car>) message.getPayload();
    }
}
