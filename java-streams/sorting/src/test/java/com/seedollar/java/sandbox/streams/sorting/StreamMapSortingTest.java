package com.seedollar.java.sandbox.streams.sorting;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMapSortingTest {

    private Map<String, Integer> persons = Maps.newHashMap();
    {
        persons.put("John", 35);
        persons.put("Eric", 41);
        persons.put("Melissa", 21);
        persons.put("Gary", 74);
        persons.put("Mary", 11);
    }

    @Test
    @DisplayName("Test to illustrate how to sort a map using stream API")
    public void testSortMapUsingStream() {
        LinkedHashMap<String, Integer> sortedPersons = persons.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (age1, age2) -> age1, LinkedHashMap::new));

        sortedPersons.entrySet().stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("Test to illustrate how to sort a map in reverse using stream API")
    public void testSortMapUsingStreamReversed() {
        LinkedHashMap<String, Integer> sortedPersons = persons.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (age1, age2) -> age1, LinkedHashMap::new));

        sortedPersons.entrySet().stream().forEach(System.out::println);
    }
}
