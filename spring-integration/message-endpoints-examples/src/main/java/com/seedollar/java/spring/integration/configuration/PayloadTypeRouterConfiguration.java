package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 * Created by seedollar on 10/6/16.
 */
@Configuration
@EnableIntegration
public class PayloadTypeRouterConfiguration {

    @Bean
    public MessagingTemplate payloadTypeRouterMessagingTemplateInvoker() {
        return new MessagingTemplate(startPayloadTypeRouterChannel());
    }

    @Bean
    public MessageChannel startPayloadTypeRouterChannel() {
        return new QueueChannel();
    }

    @Bean(name = "stringTypeChannel")
    public QueueChannel stringTypeChannel() {
        return new QueueChannel();
    }

    @Bean(name = "integerTypeChannel")
    public QueueChannel integerTypeChannel() {
        return new QueueChannel();
    }

    @Bean(name = "booleanTypeChannel")
    public QueueChannel booleanTypeChannel() {
        return new QueueChannel();
    }

    @Bean(name = "unknownTypeChannel")
    public QueueChannel unknownTypeChannel() {
        return new QueueChannel();
    }

    @Bean(name = "errorTypeChannel")
    public QueueChannel errorTypeChannel() {
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

    @Bean
    @Router(inputChannel = "startPayloadTypeRouterChannel")
    public PayloadTypeRouter payloadTypeRouter() {
        PayloadTypeRouter payloadTypeRouter = new PayloadTypeRouter();
        payloadTypeRouter.setChannelMapping("java.lang.String", "stringTypeChannel");
        payloadTypeRouter.setChannelMapping("java.lang.Integer", "integerTypeChannel");
        payloadTypeRouter.setChannelMapping("java.lang.Boolean", "booleanTypeChannel");
        payloadTypeRouter.setChannelMapping("java.lang.Exception", "errorTypeChannel");
        payloadTypeRouter.setDefaultOutputChannel(unknownTypeChannel());
        payloadTypeRouter.setResolutionRequired(false);
        return payloadTypeRouter;
    }
}
