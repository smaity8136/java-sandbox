package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 * Created by seedollar on 10/4/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration.endpoint")
public class FilterMessageEndpointConfiguration {

    @Bean
    public MessagingTemplate filterMessageTemplateInvoker() {
        return new MessagingTemplate(startFilterChannel());
    }

    @Bean
    public QueueChannel startFilterChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel secondFilterChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel completeFilterChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel discardFilterChannel() {
        return new QueueChannel();
    }

    /**
     * Define a default Poller to fufill @ServiceActivator definitions
     *
     * @return
     */
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(10));
        return pollerMetadata;
    }
}
