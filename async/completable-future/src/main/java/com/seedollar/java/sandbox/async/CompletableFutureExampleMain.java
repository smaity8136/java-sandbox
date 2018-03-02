package com.seedollar.java.sandbox.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExampleMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            String result = "stageOne";
            System.out.println(result);
            return result;
        });

        completableFuture.thenApplyAsync(rs -> {
            System.out.println("stageTwo -> toUpper");
            return rs.toUpperCase();
        }).thenAccept(rs -> System.out.println("stageThree = " + rs));

        Thread.sleep(2000);

    }
}
