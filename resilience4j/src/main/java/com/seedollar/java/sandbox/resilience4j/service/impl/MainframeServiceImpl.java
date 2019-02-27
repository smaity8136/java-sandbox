package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.service.MainframeService;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongSupplier;
import java.util.function.Supplier;


@Service
public class MainframeServiceImpl implements MainframeService {

    private static final Logger logger = LoggerFactory.getLogger(MainframeServiceImpl.class);

    private Retry mainframeRetry;
    private TimeLimiter bookValueTimeLimiter;

    private AtomicInteger counter = new AtomicInteger(0);

    public MainframeServiceImpl(Retry mainframeRetry, TimeLimiter bookValueTimeLimiter) {
        this.mainframeRetry = mainframeRetry;
        this.bookValueTimeLimiter = bookValueTimeLimiter;
    }

    @Override
    public String syncAccount(String accountId) {
        // Decorate mainframeRetry with supplier function
        CheckedFunction0<String> mainframeSyncRetryFunction = Retry.decorateCheckedSupplier(mainframeRetry, () -> mainframeSync(accountId));
        return Try.of(mainframeSyncRetryFunction)
                .get();
    }

    public String batchJobs() {
        CheckedFunction0<String> mainframeSyncRetryFunction = Retry.decorateCheckedSupplier(mainframeRetry, this::triggerBatchJobs);
        return Try.of(mainframeSyncRetryFunction)
                .get();
    }

    @Override
    public long calculateBookValue() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Supplier<Future<Long>> calculateBookValueSupplier = () -> executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("calculate book value process interrupted.");
            }
            return ThreadLocalRandom.current().nextLong();
        });

        Callable<Long> longRunningCall = TimeLimiter.decorateFutureSupplier(bookValueTimeLimiter, calculateBookValueSupplier);
        return Try.ofCallable(longRunningCall).onFailure(ex -> logger.info("Calculating book value timed out, returning 9999 default.")).getOrElse(9999L);
    }

    private String mainframeSync(String accountId) {
        logger.info("Executing mainframe sync...");
        switch (accountId) {
            case "fail":
                throw new ArithmeticException();
            case "ignore":
                throw new IllegalArgumentException();
            default:
                return "success";
        }
    }

    private String triggerBatchJobs() {
        int counter = this.counter.incrementAndGet();
        logger.info("Triggering batch jobs...");
        if (counter % 3 == 0) {
            return "retryOnResultSuccess";
        }
        return "retryMe";
    }
}
