package com.seedollar.java.streams.reuse;

import static org.asynchttpclient.Dsl.*;

import org.asynchttpclient.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReuseStreamTest {

    final static String WEATHER_URL = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2018-04-01&enddate=2018-04-30&tp=24&format=csv&key=54a4f43fc39c435fa2c143536183004";
    Pattern pat = Pattern.compile("\\n");
    Pattern comma = Pattern.compile(",");

    @Test
    public void testReuseStreamFromSource() throws ExecutionException, InterruptedException {


        CompletableFuture<Stream<String>> csv = asyncHttpClient()
                .prepareGet(WEATHER_URL)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .thenApply(pat::splitAsStream);

        CompletableFuture<IntStream> temps = csv.thenApply(str -> str
                .filter(w -> !w.startsWith("#"))
                .skip(1)
                .map(line -> comma.splitAsStream(line).skip(2).findFirst().get())
                .mapToInt(Integer::parseInt));

        temps.get().forEach(tempC -> System.out.println("tempC = " + tempC));

    }

    @Test
    public void testReuseStreamUsingMemoizing() throws ExecutionException, InterruptedException {
        CompletableFuture<Stream<String>> csv = asyncHttpClient()
                .prepareGet(WEATHER_URL)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .thenApply(pat::splitAsStream);

        CompletableFuture<List<Integer>> temps = csv.thenApply(str -> str
                .filter(w -> !w.startsWith("#"))
                .skip(1)
                .map(line -> comma.splitAsStream(line).skip(2).findFirst().get())
                .mapToInt(Integer::parseInt))
                .thenApply(str -> str.boxed().collect(Collectors.toList()));

        Supplier<Stream<Integer>> stream = () -> temps.join().stream();

        System.out.println("even temp count = " + stream.get().filter(temp -> temp % 2 == 0).count());


    }
}
