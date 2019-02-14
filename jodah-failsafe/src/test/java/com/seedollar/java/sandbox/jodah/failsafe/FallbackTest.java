package com.seedollar.java.sandbox.jodah.failsafe;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.Fallback;
import net.jodah.failsafe.RetryPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FallbackTest {

    @Test
    @DisplayName("Show how to configure a Fallback policy when should be used when a policy is exhausted")
    public void testFallback() {
        RetryPolicy<Object> retryPolicy = new RetryPolicy<>().withMaxRetries(2);
        Fallback<Object> fallback = Fallback.of("Fallback Response");

        Object result = Failsafe.with(retryPolicy, fallback).get(() -> {
            throw new IllegalArgumentException();
        });

        Assertions.assertEquals("Fallback Response", result);
    }
}
