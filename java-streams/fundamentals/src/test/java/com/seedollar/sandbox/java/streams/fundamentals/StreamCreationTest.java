package com.seedollar.sandbox.java.streams.fundamentals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class StreamCreationTest {

    @Test
    @DisplayName("Create a empty stream")
    public void testCreateEmptyStream() {
        Stream stream = Stream.empty();
        Assertions.assertTrue(stream.count() == 0);
    }

    @Test
    @DisplayName("Create stream from Arrays")
    public void testCreateStreamFromArray() {
        Stream<String> streamArray = Arrays.stream(new String[]{"one", "two", "threee"});
        streamArray.forEach(System.out::println);
    }

    @Test
    @DisplayName("Create stream using Stream.builder()")
    public void testCreateStreamUsingStreamBuilderMethod() {
        Stream.Builder<Double> streamBuilder = Stream.<Double>builder().add(24d).add(199.42).add(49.452);
        streamBuilder.accept(0.452);
        Assertions.assertEquals(4, streamBuilder.build().count());
    }

    @Test
    @DisplayName("Create stream from Stream.generate() to create an infinite stream")
    public void testCreateStreamFromGenerateMethod() {
        // The generate will create an infinite stream, so you have to place a limit on it.
        Stream<Integer> integerStream = Stream.generate(() -> Lists.newArrayList(1, 2, 3, 4, 5)).flatMap(List::stream).limit(10);
        integerStream.forEach(System.out::println);
    }

    @Test
    @DisplayName("Create stream from Stream.iterate() to create an infinite stream with a UnaryOperator to perform the increment")
    public void testCreateStreamFromIterateMethod() {
        Stream<Integer> iterateStream = Stream.iterate(1, (n) -> n * 2).limit(10);
        iterateStream.forEach(System.out::println);
    }

    @Test
    @DisplayName("Create stream from file")
    public void testCreateStreamFromFile() throws IOException, URISyntaxException {
        Stream<String> linesStream = Files.lines(Paths.get(getClass().getClassLoader().getResource("numbers.txt").toURI()));
        linesStream.map(val -> val.substring(0, val.length()-1)).forEach(System.out::println);
    }

    @Test
    @DisplayName("Create stream using IntStream")
    public void testCreateIntStream() {
        IntStream intStream = IntStream.range(1, 130);
        intStream.forEach(System.out::println);
    }
}
