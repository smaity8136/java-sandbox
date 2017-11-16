package com.seedollar.java.sandbox.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;

/**
 * Created by seedollar on 11/16/17.
 */
public class BinarySearchApplication {

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchApplication.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.error("Must provide at least 1 parameter to search!");
            System.exit(-1);
        }
        // Keep in mind that for a binary search to work, the set needs to be already sorted.
        final int[] sortedNumbers = {2,4,6,8,10,12,14,16,18,20};
        final int target = Integer.valueOf(args[0]);

        boolean result = binarySearch(sortedNumbers, target);
        System.out.println("result = " + result);

    }

    private static boolean binarySearch(int[] numbers, int target) {
        if (numbers.length == 0) {
            return false;
        }

        int low = 0;
        int high = numbers.length -1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // Divide by 2

            if (numbers[mid] < target) {
                low = mid + 1;
            } else if (numbers[mid] > target) {
                high = mid - 1;
            } else if (numbers[mid] == target) {
                return true;
            }
        }
        return false;
    }
}
