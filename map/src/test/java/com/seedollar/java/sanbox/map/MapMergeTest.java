package com.seedollar.java.sanbox.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MapMergeTest {

  @Test
  @DisplayName("Test the map.merge() method")
  void testMapMerge() {
    List<String> berserkCharacters = List.of("Guts", "Casca", "Hawk", "Casca", "Hawk", "Casca");
    Map<String, Integer> characterHitMap = new HashMap<>();
    // Merge will trigger a remapping BiFunction, where you can deal with the current value vs the next value and decide what value will be replaced for the current key.
    berserkCharacters.forEach(name -> characterHitMap.merge(name, 1, Integer::sum));
    System.out.println("characterHitMap = " + characterHitMap);
  }

  @Test
  @DisplayName("Test the map.compute() method")
  void testCompute() {
    List<String> southParkCharacters = List.of("Stan", "Kyle", "Eric", "Kenny", "Eric");
    Map<String, Integer> characterHitMap = new HashMap<>();
    southParkCharacters.forEach(name -> characterHitMap.compute(name, (key, current) -> current == null ? 1:current +1));
    System.out.println("characterHitMap = " + characterHitMap);

  }


}
