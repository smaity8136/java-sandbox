package com.seedollar.java.sandbox.javaslang;

import javaslang.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Created by seedollar on 1/4/17.
 */
public class TryExampleTest {

    @Test
    @DisplayName("Shows how to use a Javaslang Try monadic container type")
    void testTry() {

        Try<Boolean> isEvenFunction = Try.of(() -> {
            if (ThreadLocalRandom.current().nextInt() % 2 == 0) {
                return Boolean.TRUE;
            }
            throw new IllegalArgumentException("Not even!");
        });

        Assumptions.assumeTrue(isEvenFunction.getOrElse(Boolean.FALSE), "Not true, number was not even!");
    }

    @Test
    @DisplayName("This Try will fail")
    void testTryFailed() {
        Try<Integer> wasDivisibleTry = Try.of(() -> 11 / 0);
        Assertions.assertTrue(wasDivisibleTry.isFailure());
    }

    @Test
    @DisplayName("Try that fails, and we return a default value")
    void testTryWithDefaultValue() {
        Try<Integer> numberDivisibleTry = Try.of(() -> 11 / 0);
        Assertions.assertEquals(3, numberDivisibleTry.getOrElse(3).intValue());
    }

    @Test
    @DisplayName("Try that fails, and we return a custom Exception implementation")
    void testTryWithCustomException() {
        Try<Integer> numberDivisibleTry = Try.of(() -> 11 /0);
        Assertions.assertThrows(NumberFormatException.class, () -> numberDivisibleTry.getOrElseThrow((Supplier<NumberFormatException>) NumberFormatException::new));
    }

}
