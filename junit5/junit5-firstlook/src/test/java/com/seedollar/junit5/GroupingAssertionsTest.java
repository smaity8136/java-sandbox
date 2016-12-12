package com.seedollar.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 12/12/16.
 */
public class GroupingAssertionsTest {

    @Test
    void testGroupedAssertions() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(100 % 2 == 0),
                () -> Assertions.assertFalse(97 % 2 == 0),
                () -> Assertions.assertEquals(2, 2)
        );
    }
}
