package com.seedollar.java.sandbox.java12.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Java12StringApiUpdatesTest {

  @Test
  @DisplayName("Test to illustrate the new Java 12 String api methods")
  void testStringApiUpdates() {
    final String source = "This is a string";

    String result = source.transform(String::toUpperCase).indent(5);
    System.out.println("Java 12 String manipulations = " + result);
  }

}
