package com.seedollar.java.spring.integration.endpoint;

import com.seedollar.java.spring.integration.domain.Butterfly;
import com.seedollar.java.spring.integration.domain.Moth;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by seedollar on 10/4/16.
 */
@Component
public class TransformerMessageEndpoint implements Transformer {

    @Override
    @org.springframework.integration.annotation.Transformer(inputChannel = "beforeTransformerChannel", outputChannel = "afterTransformerChannel")
    public Message<?> transform(Message<?> message) {
        Moth payload = (Moth) message.getPayload();
        Butterfly newPayload = new Butterfly("YELLOW", payload.getWingSpan());
        return MessageBuilder.createMessage(newPayload, message.getHeaders());
    }
}
