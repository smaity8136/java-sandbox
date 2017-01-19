package com.seedollar.java.spring.retry.service.impl;

import com.seedollar.java.spring.retry.service.CalculationService;
import javaslang.control.Try;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

import static javaslang.API.*;

/**
 * Created by seedollar on 1/19/17.
 */
public class CalculationServiceImpl implements CalculationService {

    static CountDownLatch calculateRetryCountDownLatch = new CountDownLatch(3);
    static CountDownLatch calculateRetryExclusionCountDownLatch = new CountDownLatch(4);

    @Override
    public double calculateRetry() {
        calculateRetryCountDownLatch.countDown();
        Try tryMatch = Match(calculateRetryCountDownLatch.getCount()).of(
                Case($(2l), Try.failure(new ArithmeticException("Mock Failure"))),
                Case($(), Try.success(600397.45d))
        );

        return (double) tryMatch.get();
    }

    @Override
    public double calculateRetryExclusion() {
        calculateRetryExclusionCountDownLatch.countDown();
        Try tryMatch = Match(calculateRetryExclusionCountDownLatch.getCount()).of(
                Case($(3l), Try.failure(new ArithmeticException("Mock Failure"))),
                Case($(2l), Try.failure(new IllegalArgumentException("Mock Failure"))),
                Case($(), Try.success(86834.683d))
        );

        return (double) tryMatch.get();
    }

    @Override
    public String calculateAndRecoverSQL() throws SQLException {
        throw new SQLException("Mock Failure");
    }

    @Override
    public String calculateAndRecoverNullPointer() throws NullPointerException {
        throw new NullPointerException("Mock Failure");
    }

    @Override
    public String recoveryMethodForSQL(SQLException ex) {
        return "recoveryForSQL";
    }

    @Override
    public String recoveryMethodForNullPointer(NullPointerException ex) {
        return "recoveryForNullPointer";
    }
}
