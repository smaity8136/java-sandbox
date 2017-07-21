package com.seedollar.sandbox.springcloud.component;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/3/17.
 */
public interface Judge {

    String SENTENCE = "sentenceChannel";
    String GUILTY = "guiltyChannel";
    String INNOCENT = "innocentChannel";

    @Input(SENTENCE)
    SubscribableChannel sentence();

    @Output(GUILTY)
    MessageChannel guilty();

    @Output(INNOCENT)
    MessageChannel innocent();
}
