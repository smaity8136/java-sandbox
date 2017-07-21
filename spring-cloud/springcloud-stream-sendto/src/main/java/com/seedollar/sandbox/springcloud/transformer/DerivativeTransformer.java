package com.seedollar.sandbox.springcloud.transformer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.handler.annotation.SendTo;
import com.seedollar.sandbox.springcloud.binding.CashDerivativeProcessor;

import java.util.UUID;

/**
 * Created by seedollar on 7/5/17.
 */
@EnableBinding(CashDerivativeProcessor.class)
public class DerivativeTransformer {

    @InboundChannelAdapter(CashDerivativeProcessor.INPUT)
    public String acceptCash() {
        return UUID.randomUUID().toString();
    }

    @StreamListener(CashDerivativeProcessor.INPUT)
    @SendTo(CashDerivativeProcessor.OUTPUT)
    public String transformCash(String cash) {
        return String.valueOf(cash.chars().filter(Character::isAlphabetic).count());
    }
}
