package com.seedollar.sandbox.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.support.MessageBuilder;
import com.seedollar.sandbox.springcloud.component.Judge;

import java.util.UUID;

/**
 * Created by seedollar on 7/3/17.
 */
@EnableBinding(Judge.class)
public class JudgeService {

    private Judge judge;

    @Autowired
    public JudgeService(Judge judge) {
        this.judge = judge;
    }

    @InboundChannelAdapter(value = Judge.SENTENCE, poller = @Poller(fixedDelay = "5000"))
    public String generateSentence() {
        return UUID.randomUUID().toString();
    }

    @StreamListener(value = Judge.SENTENCE)
    public void conmmenceSentencing(String sentence) {

        sentence.chars().forEach(chInt -> {
            if (Character.isAlphabetic(chInt)) {
                judge.guilty().send(MessageBuilder.withPayload(String.format("The character [%c] is guilty of being alphabetic!", ((char) chInt))).build());
            } else {
                judge.innocent().send(MessageBuilder.withPayload(String.format("The character [%c] is innocent of not being alphabetic", ((char) chInt))).build());
            }
        });
    }
}
