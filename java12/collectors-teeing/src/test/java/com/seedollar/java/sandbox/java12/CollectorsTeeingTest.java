package com.seedollar.java.sandbox.java12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTeeingTest {

    @Test
    @DisplayName("Java12 illustration of new aggregating collectors function called teeing()")
    public void testCollectorsTeeing() {
        // The teeing() method accepts 3 parameters:
        // 1) A collector function to aggregate to a value
        // 2) Another collector function to aggregate to another value
        // 3) A Bi-Function which accepts the 2 values calculated above
        // So in this case, we are summing the numbers 1-6 for the first collector parameter = 21, then we are counting the numbers in the stream = 6, then we are executing the Bi-Function to generate
        // the expected value: 21/6 = 3.5
        Double expectedValue = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.teeing(
                        Collectors.summingDouble(num -> num),
                        Collectors.counting(),
                        (sumDouble, counter) -> sumDouble / counter));

        Assertions.assertEquals(3.5f, expectedValue.doubleValue());

    }
}
