package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RemoveDuplicatesFromIntArrayTest {

    @Test
    public void testRemoveDuplicatesFromIntArray() {
        int[] numbers = new int[]{53, 64, 2, 11, 65, 21, 53, 76, 231, 84, 12, 11};
        int[] results = new int[numbers.length];

        Arrays.sort(numbers);
        System.out.println("numbers = " + Arrays.toString(numbers));
        results[0] = numbers[0];
        int current = numbers[0];
        for (int x = 1; x < numbers.length; x++) {
            if (current != numbers[x]) {
                results[x] = numbers[x];
            }
            current = numbers[x];
        }
        System.out.println("results = " + Arrays.toString(results));
    }
}
