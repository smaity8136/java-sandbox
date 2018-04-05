package com.seedollar.java.sandbox.java10;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class UnmodifiableCollectionsTest {

    @Test
    @DisplayName("Test the List.copyOf() method that will make a mutable list immutable")
    public void testListCopyOf() {
        // Create a mutable list
        List<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        names.add("test3");

        List<String> immutableNames = List.copyOf(names);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableNames.add("test4"));
    }

    @Test
    @DisplayName("Test the Map.copyOf() method that will make an mutable map immutable")
    public void testMapCopyOf() {
        Map<Integer,  String> indexMap = Maps.newHashMap();
        indexMap.put(1, "Carl Sagon");
        indexMap.put(2, "Estelle Franks");
        indexMap.put(3, "Bobby Newton");

        Map<Integer, String> immutableIndexMap = Map.copyOf(indexMap);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableIndexMap.put(4, "Tom Gunn"));
    }

    @Test
    @DisplayName("Test the Set.copyOf() method that will make an mutable set immutable")
    public void testSetCopyOf() {
        Set<Integer> lottoNumbers = Sets.newHashSet();
        IntStream.range(0, 10).forEach(num -> lottoNumbers.add(num));

        Set<Integer> immutableLottoNumbers = Set.copyOf(lottoNumbers);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableLottoNumbers.add(53244));
    }



}
