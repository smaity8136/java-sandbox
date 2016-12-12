package com.seedollar.junit5;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by seedollar on 12/12/16.
 */
public class AssumptionsTest {

    @Test
    @DisplayName("An assumption test which will pass if the random number is divisible by 2, else ignore the test")
    void testAssumptionDivisibleByTwo() {
        final int randomValue = ThreadLocalRandom.current().nextInt();
        Assumptions.assumeTrue(randomValue % 2 == 0);
    }
}
