package com.seedollar.java.sandbox.java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethodsTest {

    @Test
    @DisplayName("Test some of the new Java 9 collection factory methods")
    public void testCollectionFactoryMethods() {
        List<String> immutableList = List.of("test1", "test2", "test3");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableList.add("test4"));

        // IllegalArgumentException will be thrown because of duplicates
        Assertions.assertThrows(IllegalArgumentException.class, () -> Set.of(45, 45, 234, 32, 45, 2, 5, 23));

        Map<Integer, Boolean> immutableMap = Map.of(1, true, 2, true, 3, false, 4, true, 5, true, 6, false);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableMap.put(8, false));

        // We cannot add null objects to immutable collections
        Assertions.assertThrows(NullPointerException.class, () -> List.of(1, 2, null));
        Assertions.assertThrows(NullPointerException.class, () -> Set.of(1, 2, null));
        Assertions.assertThrows(NullPointerException.class, () -> Map.of(1, true,  null, false));
    }
}
