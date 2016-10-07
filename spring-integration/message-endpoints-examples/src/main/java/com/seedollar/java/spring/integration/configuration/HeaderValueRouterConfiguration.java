package com.seedollar.java.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/6/16.
 */
@Configuration
@EnableIntegration
@ComponentScan("com.seedollar.java.spring.integration")
public class HeaderValueRouterConfiguration {

    @Bean
    public MessagingTemplate headerValueRouterMessagingTemplateInvoker() {
        return new MessagingTemplate(startHeaderValueRouterChannel());
    }

    @Bean
    public MessageChannel startHeaderValueRouterChannel() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel bmwCarChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel audiCarChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel toyotaCarChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel unknownCarChannel() {
        return new QueueChannel();
    }

    @Bean
    @Router(inputChannel = "startHeaderValueRouterChannel")
    public HeaderValueRouter headerValueRouter() {
        HeaderValueRouter carMakeHeaderValueRouter = new HeaderValueRouter("carMake");
        carMakeHeaderValueRouter.setChannelMapping("BMW", "bmwCarChannel");
        carMakeHeaderValueRouter.setChannelMapping("Audi", "audiCarChannel");
        carMakeHeaderValueRouter.setChannelMapping("Toyota", "toyotaCarChannel");
        carMakeHeaderValueRouter.setResolutionRequired(false);
        carMakeHeaderValueRouter.setDefaultOutputChannel(unknownCarChannel());
        return carMakeHeaderValueRouter;
    }


}
