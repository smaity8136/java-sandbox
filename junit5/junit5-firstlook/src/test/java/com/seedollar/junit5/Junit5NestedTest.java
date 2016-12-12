package com.seedollar.junit5;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seedollar on 12/12/16.
 */
public class Junit5NestedTest {

    private List<String> names;

    @BeforeEach
    void init() {
        names = new ArrayList<String>();
    }

    @Test
    @DisplayName("Check that the names list is empty")
    void testIsEmpty() {
        Assertions.assertTrue(names.isEmpty());
    }

    /**
     * This nested test class will execute as part of the parent test class so that we use the same initialization methods.
     */
    @Nested
    class NestedWhenEmptyTest {

        @Test
        public void removeThrowsException() {
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> names.remove(3));
        }
    }

    @Nested
    class NestedWhenAddingOneElementTest {

        @Test
        public void testAddOneElement() {
            names.add("MockerOne");
            Assertions.assertEquals(1, names.size());
        }

    }

}
