package com.seedollar.java.spring.retry.configuration;

import com.seedollar.java.spring.retry.listener.AlertingRetryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.UniformRandomBackOffPolicy;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by seedollar on 1/19/17.
 */
@Configuration
public class RetryTemplateConfiguration {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(backOffPolicy());
        retryTemplate.setRetryPolicy(retryPolicy());
        retryTemplate.registerListener(retryListenerSupport());
        return retryTemplate;
    }

    @Bean
    public BackOffPolicy backOffPolicy() {
        UniformRandomBackOffPolicy uniformRandomBackOffPolicy = new UniformRandomBackOffPolicy();
        uniformRandomBackOffPolicy.setMaxBackOffPeriod(4000);
        uniformRandomBackOffPolicy.setMinBackOffPeriod(2000);
        return uniformRandomBackOffPolicy;
    }

    @Bean
    public RetryPolicy retryPolicy() {
        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(10000);
        return timeoutRetryPolicy;
    }

    @Bean
    public RetryListenerSupport retryListenerSupport() {
        return new AlertingRetryListener();
    }

}
