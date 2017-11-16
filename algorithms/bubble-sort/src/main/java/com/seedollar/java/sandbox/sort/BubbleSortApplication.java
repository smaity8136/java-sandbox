package com.seedollar.java.sandbox.sort;

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

        int n = numbers.length;
        int k;

        for (int m=n; m >= 0; m--) {
            for (int i=0; i < n-1; i++) {
                k = i + 1;
                if (numbers[i] > numbers[k]) {
                    performSwap(i, k, numbers);
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
