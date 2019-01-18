package com.seedollar.java.sandbox.sort.quicksort;

import java.util.Arrays;

/**
 * Quicksort happens in place, meaning no additional data structures are required to perform the sort, keeping the memory footprint the same. The numbers are sorted on the same array.
 */
public class QuickSortApplication {

    public static void main(String[] args) {
        int[] numbers = new int[]{6, 5, 3, 9, 1, 8, 7, 2, 4};

        System.out.println("numbers = " + Arrays.toString(numbers));
        quickSort(numbers);
        System.out.println("sorted numbers = " + Arrays.toString(numbers));

    }

    public static void quickSort(int[] array) {
        recursiveQuickSort(array, 0, array.length - 1);
    }

    private static void recursiveQuickSort(int[] array, int startIdx, int endIdx) {

        int idx = partition(array, startIdx, endIdx);

        if (startIdx < idx - 1) {
            recursiveQuickSort(array, startIdx, idx - 1);
        }

        if (endIdx > idx) {
            recursiveQuickSort(array, idx, endIdx);
        }

    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[left];

        while (left <= right) {

            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;

            }

        }
        return left;

    }

    private static void swap(int[] array, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }


}
