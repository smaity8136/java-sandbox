package com.seedollar.java.sandbox.javaslang;

import javaslang.Function0;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class FunctionMemoizationTest {

    @Test
    @DisplayName("Shows how you can create a memoization function using JavaSlang")
    void testFunctionMemoization() {
        // Fist create a no-arg function to generate a random integer number.
        Function0<Double> randomLongFunction = Function0.of(Math::random).memoized();

        // We then apply the function.
        Double random1 = randomLongFunction.apply();
        // The second time we apply the function, the cached value will be returned.
        Double random2 = randomLongFunction.apply();
        Assertions.assertEquals(random1, random2);
    }
}
