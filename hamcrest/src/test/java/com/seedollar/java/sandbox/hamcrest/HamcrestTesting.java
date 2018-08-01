package com.seedollar.java.sandbox.hamcrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notANumber;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class HamcrestTesting {

    @Test
    @DisplayName("assertThat with containsString()")
    public void testAssertThat_With_ContainsString() {
        assertThat("you are experienced", containsString("are"));
        assertThat("you are experienced", both(containsString("are")).and(containsString("ien")));
    }

    @Test
    @DisplayName("assertThat with Collections")
    public void testAssertThat_With_Collections() {
        List<String> actual = Arrays.asList("one", "two", "four");
        assertThat(actual, hasItems("one", "four"));
        assertThat(actual, everyItem(containsString("o")));
        assertThat(actual, containsInRelativeOrder("two", "four"));
        assertThat(actual, containsInAnyOrder("four", "two", "one"));
    }

    @Test
    @DisplayName("assertThat with core matchers")
    public void testAssertThat_with_CoreMatchers() {
        String actual = "ozymandias";
        String another = "bysshe";
        assertThat(actual, allOf(equalTo("ozymandias"), startsWith("ozy")));
        assertThat(actual, endsWith("ias"));
        // Does not match "ozzborne" and "ozymandias" returning false, and then not(false) = true
        assertThat(actual, not(allOf(equalTo("ozzborne"), equalTo("ozymandias"))));
        assertThat(actual, anyOf(equalTo("ozzborne"), equalTo("ozymandias")));
        // Assert that 5 or 7 do not match the actual of 7
        assertThat(actual.length(), not(either(equalTo(5)).or(equalTo(7))));
        assertThat(actual, not(sameInstance(another)));
    }

    @Test
    @DisplayName("is matcher")
    public void testIs() {
        final String actual = "curie";
        assertThat(actual, is(equalTo("curie")));
        assertThat(actual, isA(String.class));
        assertThat(actual, is(anything()));
    }

    @Test
    @DisplayName("numbers")
    public void testNumbers() {
        assertThat(Double.NaN, is(notANumber()));
    }

    @Test
    @DisplayName("Comparisons e.g. greater than, less than etc.")
    public void testComparisons() {
        final int number = 49723;
        assertThat(number, is(greaterThan(40000)));
        assertThat(number, is(greaterThanOrEqualTo(49723)));
        assertThat(number, is(lessThan(50000)));
        assertThat(number, is(lessThanOrEqualTo(49723)));
    }

    @Test
    @DisplayName("strings")
    public void testStrings() {
        final String actual = "bananaramarepublic";
        final String nullString = null;
        final String emptyString = "";
        assertThat(actual, stringContainsInOrder("ban", "rama", "lic"));
        assertThat(actual, not(blankString()));
        assertThat(nullString, is(blankOrNullString()));
        assertThat(emptyString, is(emptyString()));
        assertThat(actual, is(equalToIgnoringCase("bAnAnArAmArepublic")));
        // Only ignores trailing and leading white spaces
        assertThat(actual, is(equalToIgnoringWhiteSpace(" bananaramarepublic  ")));

    }
}
