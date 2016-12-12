package com.seedollar.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 12/12/16.
 */
public class AssertExceptionsTest {

    @Test
    @DisplayName("Tests the assertThrows() method")
    void testAssertThrows() {
        Assertions.assertThrows(NullPointerException.class, () -> {Integer value = null; value.longValue();});
    }

    @Test
    @DisplayName("Tests the expectThrows() method")
    void testExpectThrows() {
        Exception expectedException = Assertions.expectThrows(IndexOutOfBoundsException.class, () -> {
            "TestString".substring(-1);
        });

        Assertions.assertTrue(expectedException instanceof IndexOutOfBoundsException);
    }
}
