package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FindDuplicatesInArrayTest {

    @Test
    @DisplayName("Find the duplicate numbers in a array of integers")
    public void testFindDuplicatesInArray() {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 5, 11, 12};

        Arrays.sort(numbers);
        for (int x = 0; x < numbers.length-1; x++) {
            if (numbers[x] == numbers[x+1]) {
                System.out.println("duplicate: " + numbers[x]);
            }

        }
    }
}
