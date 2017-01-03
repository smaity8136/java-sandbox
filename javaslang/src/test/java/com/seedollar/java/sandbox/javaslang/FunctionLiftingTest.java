package com.seedollar.java.sandbox.javaslang;

import javaslang.Function1;
import javaslang.Function2;
import javaslang.control.Option;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class FunctionLiftingTest {

    @Test
    @DisplayName("Shows how you we apply a 'partial function' using the lift() method")
    void testFunctionLiftingTest() {
        // Given two numbers, confirm that their multiplication results in an even number.
        Function2<Integer, Integer, Integer> isEvenNumberFunction = (num1, num2) -> {
            final int result = num1 * num2;
            if (result % 2 == 0) {
                return result;
            }
            throw new RuntimeException("Not even number!");
        };

        // The option result is valid because the partial function is applied
        Option<Integer> evenNumberOption = Function2.lift(isEvenNumberFunction).apply(2, 8);
        Assertions.assertEquals(16, evenNumberOption.get().intValue());

        // The option value is None because the result of the first function was not a valid even number.
        Option<Integer> oddNumberOption = Function2.lift(isEvenNumberFunction).apply(3, 5);
        Assertions.assertEquals(Option.none(), oddNumberOption);
    }
}
