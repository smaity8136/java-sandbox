package com.seedollar.java.spring.integration.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/7/16.
 */
@Configuration
@EnableIntegration
public class RecipientListRouterConfiguration {

    @Bean
    public MessagingTemplate recipientListRouterMessagingTemplateInvoker() {
        return new MessagingTemplate(startRecipientListRouterChannel());
    }

    @Bean
    public MessageChannel startRecipientListRouterChannel() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel salesChannel () {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel infrastructureChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel developmentChannel() {
        return new QueueChannel();
    }

    @Bean
    @Router(inputChannel = "startRecipientListRouterChannel")
    public RecipientListRouter recipientListRouter() {
        RecipientListRouter recipientListRouter = new RecipientListRouter();
        recipientListRouter.setChannels(Lists.newArrayList(salesChannel(), infrastructureChannel(), developmentChannel()));
        return recipientListRouter;
    }

}
