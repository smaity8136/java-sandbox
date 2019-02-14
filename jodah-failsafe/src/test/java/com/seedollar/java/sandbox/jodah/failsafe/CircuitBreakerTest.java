package com.seedollar.java.sandbox.jodah.failsafe;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.Fallback;
import net.jodah.failsafe.RetryPolicy;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreakerTest {

    private static AtomicInteger failureCount;

    @BeforeEach
    public void init() {
        failureCount = new AtomicInteger(0);
    }

    @Test
    @DisplayName("Show how to use CircuitBreaker with fixed failure threshold")
    public void testCircuitBreakerFixedFailureThreshold() {

        RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
                .withMaxRetries(5)
                .withDelay(Duration.ofSeconds(1));

        CircuitBreaker<Object> circuitBreaker = new CircuitBreaker<>()
                .handle(IllegalArgumentException.class)
                .withFailureThreshold(3);

        Failsafe.with(retryPolicy, circuitBreaker).onComplete(e -> Assert.assertEquals(e.getAttemptCount(), 3)).get(() -> {
            System.out.println("retry" + failureCount.incrementAndGet());
            throw new IllegalArgumentException();
        });

        Assertions.assertTrue(circuitBreaker.isOpen());
    }
}
