package com.seedollar.java.sandbox.javaslang;

import javaslang.control.Try;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

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

}
