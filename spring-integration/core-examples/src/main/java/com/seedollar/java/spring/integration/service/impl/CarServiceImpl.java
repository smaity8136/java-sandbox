package com.seedollar.java.spring.integration.service.impl;

import com.google.common.collect.Maps;
import com.seedollar.java.spring.integration.domain.Car;
import com.seedollar.java.spring.integration.service.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by seedollar on 9/27/16.
 */
@Service
public class CarServiceImpl implements ItemService<Car> {

    private MessagingTemplate messagingTemplate;

    public CarServiceImpl(@Qualifier("directChannelMessagingTemplate") MessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void execute(List<Car> elements) {
        elements.stream().forEach(car -> {
            messagingTemplate.send(new Message<Car>() {
                @Override
                public Car getPayload() {
                    return car;
                }

                @Override
                public MessageHeaders getHeaders() {
                    return new MessageHeaders((Map<String, Object>) Maps.newHashMap().put("classifier", "A"));
                }
            });
        });
    }
}
