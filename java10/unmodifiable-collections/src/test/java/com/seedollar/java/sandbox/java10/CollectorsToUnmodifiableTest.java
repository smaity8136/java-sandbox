package com.seedollar.java.sandbox.java10;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsToUnmodifiableTest {

    @Test
    @DisplayName("Illustrate the new Collectors.toUnmodifiableList() method")
    public void testCollectorsToUnmodifiableList() {
        List<String> mutableNames = Lists.newArrayList("test1", "test2", "test3");
        List<String> immutableNamesList = mutableNames.stream().map(String::toUpperCase).collect(Collectors.toUnmodifiableList());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableNamesList.add("test4"));
    }

    @Test
    @DisplayName("Illustrate the new Collectors.toUnmodifiableSet() method")
    public void testCollectorsToUnmodifiableSet() {
        Set<String> mutableSet = Sets.newHashSet("test1", "test2", "test3");
        Set<String> immutableSet = mutableSet.stream().map(String::toUpperCase).collect(Collectors.toUnmodifiableSet());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableSet.add("test4"));
    }

    @Test
    @DisplayName("Illustrate the new Collectors.toUnmodifiableMap() method")
    public void testCollectorsToUnmodifiableMap() {
        Map<Integer, Boolean> mutableMap = Maps.newHashMap();
        mutableMap.put(1, true);
        mutableMap.put(2, true);
        mutableMap.put(3, true);
        mutableMap.put(4, false);

        Map<Integer, Boolean> immutableMap = mutableMap.entrySet().stream().filter(e -> e.getValue().booleanValue()).collect(Collectors.toUnmodifiableMap(e -> e.getKey(), e -> e.getValue()));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableMap.put(6, true));
    }
}
