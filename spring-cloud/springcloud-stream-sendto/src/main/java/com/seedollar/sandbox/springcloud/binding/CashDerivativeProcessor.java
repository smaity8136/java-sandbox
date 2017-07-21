package com.seedollar.sandbox.springcloud.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by seedollar on 7/5/17.
 */
public interface CashDerivativeProcessor {

    String INPUT = "cashChannel";
    String OUTPUT = "derivativeChannel";

    @Input(CashDerivativeProcessor.INPUT)
    SubscribableChannel cash();

    @Output(CashDerivativeProcessor.OUTPUT)
    MessageChannel derivatives();

}
