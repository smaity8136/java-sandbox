package com.seedollar.junit5;

import org.junit.jupiter.api.*;

import java.util.Date;

public class Junit5FirstLookTest {

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Before All Tests!!!!");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println(String.format("\n\nBefore each test - %s", new Date().getTime()));
    }

    @AfterEach
    void afterEachTest() {
        System.out.println(String.format("\n\nAfter each test - %s", new Date().getTime()));
    }

    @Test
    @DisplayName("My first junit 5 assertion!!!")
    void firstAssert() {
        System.out.println("\nfirstAssert");
        Assertions.assertTrue(true);
    }

    @Test
    @Tag("tagged")
    @DisplayName("A tagged test method")
    void taggedTest() {
        System.out.println("\ntaggedTest");
        Assertions.assertTrue(true);
    }

    @Test
    @Tag("slow")
    @DisplayName("A very slow test")
    void slowTest() throws InterruptedException {
        System.out.println("\nslowTest");
        Thread.sleep(3000);
        Assertions.assertTrue(true);
    }

    @Test
    @Disabled("disabled test using junit5")
    void disabledTest() {
        Assertions.assertFalse(false);
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("\n\nAfter All Tests!!!!");
    }
}
