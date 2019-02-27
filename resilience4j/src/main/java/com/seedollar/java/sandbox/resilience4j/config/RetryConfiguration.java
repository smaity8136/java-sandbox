package com.seedollar.java.sandbox.resilience4j.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RetryConfiguration {

    @Bean
    public RetryConfig customRetryConfig() {
        return RetryConfig.custom()
                .maxAttempts(5) // Retry 5 timers
                .waitDuration(Duration.ofSeconds(2)) // Delay of 2 seconds between each retry invocation
                .ignoreExceptions(NullPointerException.class, IllegalArgumentException.class) // Don't retry if these exceptions are thrown
                .retryExceptions(ArithmeticException.class) // Only retry if this exception is thrown
                .retryOnResult(result -> result instanceof String && result.equals("retryMe")) // Trigger a retry on a result = 'retryMe'
                .build();
    }

    @Bean
    public RetryRegistry retryRegistry() {
        return RetryRegistry.of(customRetryConfig());
    }

    @Bean
    public Retry mainframeSyncRetry() {
        return Retry.of("mainframeSyncRetry", this::customRetryConfig);
    }

}
