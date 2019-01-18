package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ReverseArrayInPlaceTest {

    @Test
    @DisplayName("Reverse an array of elements in place")
    public void testReverseArrayElementsInPlace() {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int left = 0;
        int right = numbers.length-1;
        while (left <= right) {
            swap(numbers, left, right);
            left++;
            right--;
        }
        System.out.println("numbers = " + Arrays.toString(numbers));

    }

    private void swap(int[] array, int left, int right) {
        //a = a + b;
        //b = a - b;
        //a = a - b;
        array[left] = array[left] + array[right];
        array[right] = array[left] - array[right];
        array[left] = array[left] - array[right];
    }
}
