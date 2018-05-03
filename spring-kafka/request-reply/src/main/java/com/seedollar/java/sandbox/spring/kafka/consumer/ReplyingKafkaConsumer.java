package com.seedollar.java.sandbox.spring.kafka.consumer;

import com.seedollar.java.sandbox.spring.kafka.model.Calculation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

public class ReplyingKafkaConsumer {

    @KafkaListener(topics = "${kafka.topic.request-topic}")
    @SendTo
    public Calculation listen(Calculation request) {
        int sum = request.getFirstNumber() + request.getSecondNumber();
        Calculation calculation = new Calculation();
        calculation.setAdditionalProperty("sum", Integer.valueOf(sum));
        return calculation;
    }
}
