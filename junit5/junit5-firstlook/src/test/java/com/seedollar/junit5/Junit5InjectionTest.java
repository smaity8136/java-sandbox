package com.seedollar.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.util.concurrent.atomic.AtomicInteger;

public class Junit5InjectionTest {

    private static AtomicInteger counter = new AtomicInteger(0);

    @BeforeEach
    public void init(TestInfo testInfo) {
        System.out.println("testInfo = " + testInfo);
        Assertions.assertNotNull(testInfo);
    }

    @Test
    @Tag("injection-tag")
    @DisplayName("Test to show injection for TestInfo object")
    public void testTestInfoInjection(TestInfo testInfo) {
        Assertions.assertEquals("Test to show injection for TestInfo object", testInfo.getDisplayName());
        Assertions.assertEquals("testTestInfoInjection", testInfo.getTestMethod().get().getName());
        Assertions.assertTrue(testInfo.getTags().contains("injection-tag"));
    }

    @Test
    @DisplayName("Test to show injection for TestReporter object")
    public void testTestReporterInjection(TestReporter testReporter) {
        testReporter.publishEntry("injection", "test-injection");
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    void repetitionTest(RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int sideEffect = currentRepetition * 2;
        counter.set(sideEffect);
        Assertions.assertEquals(10, repetitionInfo.getTotalRepetitions());
        Assertions.assertEquals(counter.get(), sideEffect);
    }
}
