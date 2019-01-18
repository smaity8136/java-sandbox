package com.seedollar.java.sandbox.sort.bubblesort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by seedollar on 11/16/17.
 */
public class BubbleSortApplication {

    private static final Logger logger = LoggerFactory.getLogger(BubbleSortApplication.class);

    public static void main(String[] args) {
        int[] numbers = {4,3,15,-3,7,-5,17,6};
        bubbleSort(numbers);
    }

    private static void bubbleSort(int[] numbers) {

        int size = numbers.length;
        int k;

        for (int x=0; x<size; x++) {
            for (int y=0; y < size-1; y++) {
                k = y + 1;
                if (numbers[x] > numbers[k]) {
                    performSwap(x, k, numbers);
                }
            }
            printNumbers(numbers);
        }
    }

    private static void performSwap(int i, int k, int[] numbers) {
        int temp = numbers[i];
        numbers[i] = numbers[k];
        numbers[k] = temp;
    }

    private static void printNumbers(int[] numbers) {
        StringBuilder result = new StringBuilder();
        for (int x : numbers) {
            result.append(x);
            result.append(",");
        }
        System.out.println("\n" + result.toString());
    }
}
