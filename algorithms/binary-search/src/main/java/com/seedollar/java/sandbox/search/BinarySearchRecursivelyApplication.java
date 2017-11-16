package com.seedollar.java.sandbox.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by seedollar on 11/16/17.
 */
public class BinarySearchRecursivelyApplication {

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchRecursivelyApplication.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.error("Please provide at least one parameter to search!");
            System.exit(-1);
        }

        // Keep in mind that for a binary search to work, the set needs to be already sorted.
        final int[] sortedNumbers = {2,4,6,8,10,12,14,16,18,20};
        final int target = Integer.valueOf(args[0]);

        int low = 0;
        int high = sortedNumbers.length - 1;

        boolean result = binarySearchRecursive(sortedNumbers, target, low, high);
        System.out.println("result = " + result);
    }

    private static boolean binarySearchRecursive(int[] sortedNumbers, int target, int low, int high) {
        int mid = (low + high) >>> 1; // Divide by 2

        if (high < low) {
            return false;
        }

        if (target == sortedNumbers[mid]) {
            return true;
        } else if (target < sortedNumbers[mid]) {
            return binarySearchRecursive(sortedNumbers, target, low, mid - 1);
        } else {
            return binarySearchRecursive(sortedNumbers, target, mid + 1, high);
        }
    }
}
