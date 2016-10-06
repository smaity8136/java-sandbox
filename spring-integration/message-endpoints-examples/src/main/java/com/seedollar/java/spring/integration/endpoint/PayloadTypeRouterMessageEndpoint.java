package com.seedollar.java.spring.integration.endpoint;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.Message;

/**
 * Created by seedollar on 10/6/16.
 */
@MessageEndpoint
public class PayloadTypeRouterMessageEndpoint {

    @Router(inputChannel = "startPayloadTypeRouterChannel", defaultOutputChannel = "errorTypeChannel", resolutionRequired = "false")
    public String payloadTypeRouting(Message<?> message) {

        Object payload = message.getPayload();

        if (payload instanceof String) {
            return "stringTypeChannel";
        } else if (payload instanceof Integer) {
            return "integerTypeChannel";
        } else if (payload instanceof Boolean) {
            return "booleanTypeChannel";
        } else if (payload instanceof Exception) {
            return "fail"; // Make sure "resolutionRequired" is set to false, forcing failed resolutions to send to the defaultOutputChannel.
        } else {
            return "unknownTypeChannel";
        }
    }
}
