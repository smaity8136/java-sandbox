package com.seedollar.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 12/12/16.
 */
public class Junit5LambdaAssertionsTest {

    @Test
    @DisplayName("Test which illustrates the evaluation of the assertion message lazily.")
    public void testLambdaAssertion() {
        Assertions.assertEquals(3 == 2, true, () -> "3 not equal to 2!");
    }
}
