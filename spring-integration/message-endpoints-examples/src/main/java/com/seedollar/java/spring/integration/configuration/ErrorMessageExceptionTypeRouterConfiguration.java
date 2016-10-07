package com.seedollar.java.spring.integration.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/7/16.
 */
@Configuration
@EnableIntegration
public class ErrorMessageExceptionTypeRouterConfiguration {

    @Autowired
    ApplicationContext context;

    @Bean
    public MessagingTemplate errorMessageExceptionTypeRouterMessagingTemplateInvoker() {
        return new MessagingTemplate(startErrorMessageExceptionTypeRouterChannel());
    }

    @Bean
    public MessageChannel startErrorMessageExceptionTypeRouterChannel() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel checkedExceptionChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel uncheckedExceptionChannel() {
        return new QueueChannel();
    }

    @Bean
    @Router(inputChannel = "startErrorMessageExceptionTypeRouterChannel")
    public ErrorMessageExceptionTypeRouter errorMessageExceptionTypeRouter() {
        ErrorMessageExceptionTypeRouter errorMessageExceptionTypeRouter = new ErrorMessageExceptionTypeRouter();
        errorMessageExceptionTypeRouter.setApplicationContext(context);
        errorMessageExceptionTypeRouter.setChannelMapping("java.lang.IllegalArgumentException", "uncheckedExceptionChannel");
        errorMessageExceptionTypeRouter.setChannelMapping("java.lang.NullPointerException", "uncheckedExceptionChannel");
        errorMessageExceptionTypeRouter.setChannelMapping("java.io.IOException", "checkedExceptionChannel");
        return errorMessageExceptionTypeRouter;
    }




}
