package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrintPairsFromArrayThatEqualSumTest {

    @Test
    @DisplayName("Given an array of numbers and a sum value, we attempt to find a pair of numbers that when summed will be equal to the sum value specified")
    public void testPrintPairForSum() {

        int[] numbers = new int[] {0,1,2,3,4,5,6,7,8,9,10};

        final int sum = 4;

        showPairs(numbers, sum);

    }

    private void showPairs(int[] numbers, int sum) {

        int first, second;

        for (int x=0; x<numbers.length; x++) {
            first = numbers[x];
            for (int y=x+1; y<numbers.length; y++) {
                second = numbers[y];

                if (first + second == sum) {
                    System.out.println(String.format("%d + %d = %d", first, second, sum));
                }
            }
        }
    }
}
