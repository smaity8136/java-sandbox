package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllPermutationsStringRecursivelyTest {

    @Test
    @DisplayName("Illustrate how to print all- permutations of a string using recursion")
    public void testPrintAllPurmutationsOfStringRecursively() {
        final String str = "XY";
        permutation("", str);
    }

    private void permutation(String permanent, String word) {
        System.out.println(String.format("permanent = %s, word = %s", permanent,  word));
        if (word.isEmpty()) {
            System.out.println(permanent + word);
        } else {
            for (int i = 0; i < word.length(); i++){
                permutation(permanent + word.charAt(i), word.substring(0, i)
                        + word.substring(i + 1));
            }
        }
    }
}
