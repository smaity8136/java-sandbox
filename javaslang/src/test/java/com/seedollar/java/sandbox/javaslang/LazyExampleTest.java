package com.seedollar.java.sandbox.javaslang;

import javaslang.Lazy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by seedollar on 1/4/17.
 */
public class LazyExampleTest {

    @Test
    @DisplayName("Shows how you can implement the Lazy JavaSlang implementation")
    void testLazy() {
        // Lazy is memoized, and is therefore referential transparent
        Lazy<Integer> randomIntegerFunction = Lazy.of(() -> ThreadLocalRandom.current().nextInt());

        Assertions.assertFalse(randomIntegerFunction.isEvaluated());
        Integer randomInt1 = randomIntegerFunction.get();

        Assertions.assertTrue(randomIntegerFunction.isEvaluated());
        // The second invocation will return the cached integer from the first invocation above.
        Integer randomInt2 = randomIntegerFunction.get();
        Assertions.assertEquals(randomInt1, randomInt2);
    }
}
