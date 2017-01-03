package com.seedollar.java.sandbox.javaslang;

import javaslang.Function1;
import javaslang.Function2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class FunctionCurringTest {

    @Test
    @DisplayName("Shows how you apply curring to JavaSlang functions")
    void functionCurringTest() {
        // Given a function which adds 2 numbers, we will then create a curried function which fixes the first parameter to 5.
        Function2<Integer, Integer, Integer> sumFunction = (num1, num2) -> num1 + num2;
        // Create the curried function which forces the first parameter value to equal 5.
        Function1<Integer, Integer> add5CurriedFunction = sumFunction.curried().apply(5);
        Assertions.assertEquals(11, add5CurriedFunction.apply(6).intValue());
    }
}
