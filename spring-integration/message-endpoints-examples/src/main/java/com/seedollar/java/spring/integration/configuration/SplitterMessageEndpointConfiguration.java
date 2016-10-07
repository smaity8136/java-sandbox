package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/7/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration.endpoint")
public class SplitterMessageEndpointConfiguration {

    @Bean
    public MessagingTemplate splitterMessagingTemplateInvoker() {
        return new MessagingTemplate(startSplitterChannel());
    }

    public MessageChannel startSplitterChannel() {
        return new DirectChannel();
    }


}
