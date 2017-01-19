package com.seedollar.java.spring.retry.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

/**
 * Created by seedollar on 1/19/17.
 */
public class AlertingRetryListener extends RetryListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(AlertingRetryListener.class);

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        logger.warn("CLOSING alert...");
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        logger.error("ERROR alert...");
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        logger.error("OPENING alert...");
        return true;
    }
}
