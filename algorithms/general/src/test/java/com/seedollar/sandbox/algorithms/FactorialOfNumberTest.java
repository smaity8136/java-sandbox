package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The factorial of a number consists of multiplying the positive integers of a sequence e.g.:
 *
 * The factorial of 5: 1 x 2 x 3 x 4 x 5 = 120
 * The factorial of 6: 1 x 2 x 3 x 4 x 5 x 6 = 720
 */
public class FactorialOfNumberTest {

    @Test
    @DisplayName("Illustrate how to calculate the factorial of a number recursively")
    public void calculateFactorialOfNumberRecursively() {
        Assertions.assertEquals(120, recursiveFactorial(5));
        System.out.println("Factorial: " + recursiveFactorial(5));
    }

    @Test
    @DisplayName("Illustrate how to calculate the factorial of a number iteratively")
    public void calculateFactorialOfNumberIteratively() {
        Assertions.assertEquals(120, iterativeFactorial(5));
        System.out.println("Factorial: " + iterativeFactorial(5));

    }

    private int recursiveFactorial(int number) {
        if (number == 0) {
            return 1;
        }
        return number * recursiveFactorial(number-1);
    }

    private int iterativeFactorial(int number) {
        int result = 1;
        while (number != 0) {
            result *= number;
            number--;
        }
        return  result;
    }
}
