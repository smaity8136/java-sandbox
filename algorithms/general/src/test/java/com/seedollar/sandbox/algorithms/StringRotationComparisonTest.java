package com.seedollar.sandbox.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringRotationComparisonTest {

    @Test
    @DisplayName("Illustrate how to confirm that 2 strings are rotations of one another")
    public void testStringRotation() {
        final String str1 = "SnapCrackelPop";
        final String str2 = "PopSnapCrackel";

        String concatenated = str1 + str1;

        if (!concatenated.contains(str2)) {
            System.out.println("Strings are NOT rotations");
        } else {
            System.out.println("Strings are rotations");
        }

    }
}
