package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * An anagram is when a word can be re-arranged to form another word e.g.:
 * <p>
 * binary = brainy
 * dormitory = dirty room
 */
public class AnagramWordsTest {

    @Test
    @DisplayName("Given 2 words, confirm whether they are an anagram")
    public void testAnagramWords() {
        String word1 = "dormitory";
        String word2 = "dirty room";

        char[] word1Array = word1.replaceAll(" ", "").toCharArray();
        Arrays.sort(word1Array);
        char[] word2Array = word2.replaceAll(" ", "").toCharArray();
        Arrays.sort(word2Array);

        boolean isAnagram = true;
        for (int x = 0; x < word1Array.length; x++) {
            if (word1Array[x] != word2Array[x]) {
                System.out.println(String.format("Word 1: [%s] and Word 2: [%s] are NOT an anagram.", word1, word2));
                isAnagram = false;
                break;
            }
        }

        if (isAnagram) {
            System.out.println(String.format("Word 1: [%s] and Word 2: [%s] are an anagram.", word1, word2));
        }

    }

}
