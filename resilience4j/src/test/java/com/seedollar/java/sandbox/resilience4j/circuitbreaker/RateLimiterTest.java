package com.seedollar.java.sandbox.resilience4j.circuitbreaker;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RateLimiterTest {

    private static final Logger logger = LoggerFactory.getLogger(RateLimiterTest.class);

    private RateLimiter testRateLimiter;

    @Before
    public void init() {
        // Define a rate limiter of 2 requests every 10 seconds.
        RateLimiterConfig rateLimitConfig = RateLimiterConfig.custom()
                .limitForPeriod(2)
                .limitRefreshPeriod(Duration.ofSeconds(10))
                .build();
        this.testRateLimiter = RateLimiter.of("testRateLimiter", rateLimitConfig);
    }

    @Test
    public void testRateLimiterMetrics() {
        // The number of available permissions should match the current limitForPeriod (requests per) which is 2
        int availablePermissions = testRateLimiter.getMetrics().getAvailablePermissions();
        logger.info("No available permissions = {}", availablePermissions);

        // Decorate the rateLimiter with a function
        Runnable execution = RateLimiter.decorateRunnable(testRateLimiter, () -> logger.info("testRateLimiterMetrics - executed"));
        // Simulate 3 concurrent requests, meaning that the third request will get rate limited initially
        Try.runRunnable(execution).andThen(execution).andThen(() -> {
            // Simulate 4 more concurrent requests, meaning that there will be 4 waiting threads on the rate limiter metrics
            CompletionStage<Void> moreConcurrentRequests = CompletableFuture.allOf(CompletableFuture.runAsync(execution), CompletableFuture.runAsync(execution), CompletableFuture.runAsync(execution), CompletableFuture.runAsync(execution));
            CompletionStage<Integer> retrieveNoWaitingThreads = CompletableFuture.supplyAsync(() -> {
                RateLimiter.Metrics metrics = testRateLimiter.getMetrics();
                int numberOfWaitingThreads = metrics.getNumberOfWaitingThreads();
                logger.info("No of waiting threads = {}", numberOfWaitingThreads);
                return numberOfWaitingThreads;
            });
            moreConcurrentRequests.thenCombine(retrieveNoWaitingThreads, (v, r) -> v).toCompletableFuture().join();
        });

    }
}
