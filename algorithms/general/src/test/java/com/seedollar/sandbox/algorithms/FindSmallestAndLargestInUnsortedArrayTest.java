package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FindSmallestAndLargestInUnsortedArrayTest {

    @Test
    @DisplayName("Test which shows how you can find the smallest and largest numbers in an unsorted integer array")
    public void testFindNumbers() {

        int[] numbers = new int[] {398,3,56,123,4,12,76,1213,6565,766,215};

        // Set the smallest to the highest integer value possible initially
        int smallest = Integer.MAX_VALUE;
        // Set the largest to the lowest integer value possible initially
        int largest = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (smallest > number) {
                smallest = number;
            }
            if (largest < number) {
                largest = number;
            }
        }

        System.out.println("smallest = " + smallest);
        System.out.println("largest = " + largest);
    }
}
