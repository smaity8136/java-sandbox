package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.service.MainframeService;
import io.github.resilience4j.retry.Retry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


@Service
public class MainframeServiceImpl implements MainframeService {

    private static final Logger logger = LoggerFactory.getLogger(MainframeServiceImpl.class);

    private Retry mainframeRetry;

    private AtomicInteger counter = new AtomicInteger(0);

    public MainframeServiceImpl(Retry mainframeRetry) {
        this.mainframeRetry = mainframeRetry;
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
