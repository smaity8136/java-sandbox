package com.seedollar.java.sandbox.javaslang;

import javaslang.collection.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by seedollar on 1/5/17.
 */
public class CollectionsExampleTest {

    @Test
    @DisplayName("Shows how you can create a Javaslang List")
    void testCreateList() {
        List<String> javaslangList = List.of("one", "two", "three");
        Assertions.assertEquals(3, javaslangList.length());
        Assertions.assertEquals("two", javaslangList.get(1));
    }

    @Test
    @DisplayName("Shows how you can sum a Javaslang List")
    void testListSum() {
        List<Integer> sumList = List.of(10, 100, 1000);
        int sum = sumList.sum().intValue();
        Assertions.assertEquals(1110, sum);
    }
}
