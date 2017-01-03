package com.seedollar.java.sandbox.javaslang;

import javaslang.Function1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/3/17.
 */
public class FunctionCompositionTest {

    @Test
    @DisplayName("Shows how you can perform composition on function calls")
    void functionCompositionTest() {
        Function1<Integer, Integer> doubleValueFunction = (num) -> num * 2;
        Function1<Integer, Integer> add100Function = (num) -> num + 100;
        // Define the final composition function of the 2 functions defined above
        Function1<Integer, Integer> computation = doubleValueFunction.andThen(add100Function);
        Assertions.assertEquals(114, computation.apply(7).intValue());

        // We can use the "compose()" method to append the function implementations. n
        // Note add100Function is applied first, then the calling function is applied (doubleValueFunction)
        Function1<Integer, Integer> composition2 = doubleValueFunction.compose(add100Function);
        Assertions.assertEquals(204, composition2.apply(2).intValue());

    }
}
