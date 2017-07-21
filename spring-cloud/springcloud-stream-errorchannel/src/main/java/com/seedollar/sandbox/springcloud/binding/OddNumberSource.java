package com.seedollar.sandbox.springcloud.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/5/17.
 */
public interface OddNumberSource {

    String OUTPUT = "oddNumberChannel";

    @Output(OddNumberSource.OUTPUT)
    MessageChannel oddNumbers();
}
