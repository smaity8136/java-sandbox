package com.seedollar.java.spring.integration.configuration;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.filter.ExpressionEvaluatingSelector;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 10/7/16.
 */
@Configuration
@EnableIntegration
public class RecipientListRouterSelectorConfiguration {

    @Bean
    public MessagingTemplate recipientListRouterSelectorMessagingTemplateInvoker() {
        MessagingTemplate messagingTemplate = new MessagingTemplate(startRecipientListRouterSelectorChannel());
        return messagingTemplate;
    }

    @Bean
    public MessageChannel startRecipientListRouterSelectorChannel() {
        return new DirectChannel();
    }

    @Bean
    public QueueChannel lowIncomeChannel () {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel middleClassChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel wealthChannel() {
        return new QueueChannel();
    }

    @Bean
    public QueueChannel governmentInterventionChannel() {
        return new QueueChannel();
    }

    @Bean
    @Router(inputChannel = "startRecipientListRouterSelectorChannel")
    public RecipientListRouter recipientListRouterSelector() {
        RecipientListRouter recipientListRouter = new RecipientListRouter();
        recipientListRouter.setRecipients(Lists.newArrayList(
                new RecipientListRouter.Recipient(lowIncomeChannel(),  new ExpressionEvaluatingSelector("headers.containsKey('silver')")),
                new RecipientListRouter.Recipient(middleClassChannel(), new ExpressionEvaluatingSelector("headers.containsKey('gold')")),
                new RecipientListRouter.Recipient(wealthChannel(), new ExpressionEvaluatingSelector("headers.containsKey('platinum')"))
                ));
        recipientListRouter.setDefaultOutputChannel(governmentInterventionChannel());
        return recipientListRouter;
    }

}
