package com.seedollar.sandbox.springloud.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/5/17.
 */
public interface PetSource {

    String OUTPUT = "petChannel";

    @Output(PetSource.OUTPUT)
    MessageChannel pets();
}
