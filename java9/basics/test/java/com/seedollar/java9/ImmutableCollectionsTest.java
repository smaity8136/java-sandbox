package com.seedollar.java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

/**
 * Created by seedollar on 2/22/17.
 */
public class ImmutableCollectionsTest {

    @Test
    @DisplayName("Test to show how we can create an EMPTY, immutable collection using Java 9 API")
    public void testCreateImmutableCollection() {
        List immutableList = List.of();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableList.add("test"));
    }

    @Test
    @DisplayName("Test to show how we can create NON-EMPTY, immutable collection using Java 9 API")
    public void testCreateNonEmptyImmutableCollection() {
        List<String> nameList = List.of("Terry", "Barry", "Larry");
        Assertions.assertThrows(java.lang.UnsupportedOperationException.class, () -> nameList.add("Anne"));
    }

    @Test
    @DisplayName("Test to show how we can create an immutable Map using Java 9 API")
    public void testCreateImmutableMap() {
        Map<Integer, String> keyPairs = Map.of(1, "One", 2, "Two");
        Assertions.assertThrows(java.lang.UnsupportedOperationException.class, () -> keyPairs.put(3, "Threee"));
    }
}
