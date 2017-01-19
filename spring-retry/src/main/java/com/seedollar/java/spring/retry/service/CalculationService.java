package com.seedollar.java.spring.retry.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.sql.SQLException;

/**
 * Created by seedollar on 1/19/17.
 */
public interface CalculationService {

    @Retryable(value = {ArithmeticException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    double calculateRetry();

    @Retryable(exclude = {IllegalArgumentException.class}, maxAttempts = 5, backoff = @Backoff(delay = 3000))
    double calculateRetryExclusion();

    /**
     * This method will fail, and invoke the "recoveryMethodForSQL()" annotated with @Recover
     * @return
     */
    @Retryable
    String calculateAndRecoverSQL() throws SQLException;

    /**
     * This method will fail, and invoke the "recoveryMethodForNullPointer()" annotated with @Recover
     * @return
     */
    @Retryable
    String calculateAndRecoverNullPointer() throws NullPointerException;


    @Recover
    String recoveryMethodForSQL(SQLException ex);

    @Recover
    String recoveryMethodForNullPointer(NullPointerException ex);

}
