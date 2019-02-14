package com.seedollar.java.sandbox.jodah.failsafe;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryPolicyTest {

    private static AtomicInteger retryCount;

    @BeforeEach
    public void init() {
        retryCount = new AtomicInteger(0);
    }

    @Test
    @DisplayName("Test showing RetryPolicy fixed duration using withDelay()")
    public void testRetryPolicyWithDelay() {
        RetryPolicy<Object> retryPolicyWithDelay = new RetryPolicy<>()
                .handle(IllegalArgumentException.class)
                .withDelay(Duration.ofSeconds(1))
                .withMaxRetries(3);

        String result = Failsafe.with(retryPolicyWithDelay).get(() -> connect(3));
        System.out.println("result = " + result);

    }

    @Test
    @DisplayName("Test showing RetryPolicy with back off delay using withBackOff()")
    public void testRetryPolicyWithBackOff() {
        // First delay is 2 seconds
        // Second delay is 4 seconds
        // Third delay is 8 seconds
        // Up to a maximum of 6 seconds
        RetryPolicy<Object> retryPolicyWithBackOff = new RetryPolicy<>()
                .handle(IllegalArgumentException.class)
                .withBackoff(2, 6, ChronoUnit.SECONDS)
                .withMaxRetries(3);

        String result = Failsafe.with(retryPolicyWithBackOff).get(() -> connect(3));
        System.out.println("result = " + result);
    }

    @Test
    @DisplayName("Test showing RetryPolicy with maxAttempts set")
    public void testRetryPolicyWithMaxAttempts() {
        RetryPolicy<Object> retryPolicyMaxAttempts = new RetryPolicy<>()
                .handle(IllegalArgumentException.class)
                .withDelay(Duration.ofSeconds(2))
                .withMaxAttempts(4); // Attempts are one less than retries, for example 2 retries = 3 attempts

        String results = Failsafe.with(retryPolicyMaxAttempts).get(() -> connect(3));
        System.out.println("results = " + results);
    }

    private String connect(final int failureThreshold) {
        if (retryCount.get() < failureThreshold) {
            System.out.println("retry " + retryCount.incrementAndGet());
            throw new IllegalArgumentException("mock failure");
        }
        return "CONNECTED " + retryCount.get();
    }

}
