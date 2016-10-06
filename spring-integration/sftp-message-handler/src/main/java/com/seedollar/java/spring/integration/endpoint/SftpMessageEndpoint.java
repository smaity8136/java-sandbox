package com.seedollar.java.spring.integration.endpoint;

import com.seedollar.java.spring.integration.handler.SftpCustomMessageHandler;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.UUID;

/**
 * Created by seedollar on 10/6/16.
 */
@MessageEndpoint
public class SftpMessageEndpoint {

    private SftpCustomMessageHandler sftpCustomMessageHandler;

    public SftpMessageEndpoint(SftpCustomMessageHandler sftpCustomMessageHandler) {
        this.sftpCustomMessageHandler = sftpCustomMessageHandler;
    }

    @ServiceActivator(inputChannel = "startSftpChannel", outputChannel = "endSftpChannel")
    public Message<?> upload(Message<?> message) {
        // Append the correlation ID header here to be the fileName
        UUID messageId = (UUID) message.getHeaders().get("id");
        Message<?> correlatedMessage = org.springframework.messaging.support.MessageBuilder.fromMessage(message).setHeader("fileName", messageId.toString()).build();
        sftpCustomMessageHandler.handleMessage(correlatedMessage);
        return correlatedMessage;
    }

}
