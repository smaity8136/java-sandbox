package com.seedollar.java.sandbox.spring.kafka.controller;

import com.seedollar.java.sandbox.spring.kafka.model.Calculation;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class CalculationController {

    @Autowired
    private ReplyingKafkaTemplate<String, Calculation, Calculation> replyingKafkaTemplate;

    @Value("${kafka.topic.request-topic}")
    private String requestTopic;

    @Value("${kafka.topic.requestreply-topic}")
    private String requestReplyTopic;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Calculation calculate(@RequestBody Calculation request) throws ExecutionException, InterruptedException {
        ProducerRecord<String, Calculation> record = new ProducerRecord<>(requestTopic, request);
        // Set the reply topic in the header
        record.headers().add(new RecordHeader(KafkaHeaders.RECEIVED_TOPIC, requestReplyTopic.getBytes()));
        // Post to request topic
        RequestReplyFuture<String, Calculation, Calculation> sendAndReceive = replyingKafkaTemplate.sendAndReceive(record);
        // confirm if producer produced successfully
        SendResult<String, Calculation> sendResult = sendAndReceive.getSendFuture().get();

        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        // get consumer record
        ConsumerRecord<String, Calculation> consumerRecord = sendAndReceive.get();
        // return consumer value
        return consumerRecord.value();

    }

}
