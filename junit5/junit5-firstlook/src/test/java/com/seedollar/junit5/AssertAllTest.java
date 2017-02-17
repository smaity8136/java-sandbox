package com.seedollar.junit5;

import javaslang.Function1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static javaslang.API.*;

/**
 * Created by seedollar on 2/17/17.
 */
public class AssertAllTest {

    interface RatingGenerator {
        Function1<Integer, String> rate = (mark) -> Match(mark).of(
                Case($(5), "Excellent"),
                Case($(4), "Very Good"),
                Case($(3), "Good"),
                Case($(2), "Average"),
                Case($(1), "Poor"));
    }

    @Test
    @DisplayName("Example of how you can use the assertAll() method")
    public void testAssertAll() {
        // Create a separate execution for each assertion
        Assertions.assertAll(
                () -> Assertions.assertEquals("Excellent", RatingGenerator.rate.apply(5)),
                () -> Assertions.assertEquals("Very Good", RatingGenerator.rate.apply(4)),
                () -> Assertions.assertEquals("Good", RatingGenerator.rate.apply(3)),
                () -> Assertions.assertEquals("Average", RatingGenerator.rate.apply(2)),
                () -> Assertions.assertEquals("Poor", RatingGenerator.rate.apply(1))
        );
    }
}
