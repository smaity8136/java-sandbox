package com.seedollar.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by seedollar on 12/12/16.
 */
public class DynamicTestingTest {

    @TestFactory
    List<DynamicTest> createDynamicTests() {
        int randomValue = ThreadLocalRandom.current().nextInt();
        System.out.println("randomValue = " + randomValue);
        return Arrays.asList(
                DynamicTest.dynamicTest("Dynamic Test 1 - Test if randomValue is greater than 0", () -> Assertions.assertTrue(randomValue > 0)),
                DynamicTest.dynamicTest("Dynamic Test 2 - Test if randomValue is divisible by 2", () -> Assumptions.assumeTrue(randomValue % 2 == 0)),
                DynamicTest.dynamicTest("Dynamic Test 3 - Test if randomValue is greater than one billion", () -> Assertions.assertTrue(randomValue > 1_000_000_000))
        );

    }
}
