package com.seedollar.java.sandbox.async;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    @Test
    public void testCompletedCompletableFuture() {
        CompletableFuture<String> completedCompletableFuture = CompletableFuture.completedFuture("completed");
        Assertions.assertTrue(completedCompletableFuture.isDone());
        Assertions.assertEquals("completed", completedCompletableFuture.getNow("now"));
    }

    @Test
    public void testAsyncCompletionStage() throws InterruptedException {
        CompletableFuture<Void> asyncProcess = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Executing async process...");
                Thread.sleep(3000);
                System.out.println("Completed async process.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Assertions.assertFalse(asyncProcess.isDone());
        Thread.sleep(5000);
        Assertions.assertTrue(asyncProcess.isDone());
    }

    @Test
    public void testApplyFunctionOnCompletionStage() {
        CompletableFuture<String> result = CompletableFuture.completedFuture("change this to uppercase")
                .thenApply(s -> s.toUpperCase());
        Assertions.assertEquals("CHANGE THIS TO UPPERCASE", result.getNow("now"));
    }

    @Test
    public void testApplyAsyncFunctionOnCompletionStage() {
        CompletableFuture<String> result = CompletableFuture.completedFuture("CHANGE THIS TO LOWERCASE")
                .thenApplyAsync(s -> {
                    Assertions.assertTrue(Thread.currentThread().isDaemon());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s.toLowerCase();
                });

        Assertions.assertEquals("change this to lowercase", result.join());
    }

    @Test
    public void testApplyThenAcceptCompletionStage() {
        StringBuilder sourceBuilder = new StringBuilder("source");
        CompletableFuture<Void> result = CompletableFuture.completedFuture(sourceBuilder.toString())
                .thenApplyAsync(s -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return sourceBuilder.append("-extra");
                })
                .thenAcceptAsync(s -> System.out.println("result = " + s));

        result.join(); // This will block
        Assertions.assertTrue(sourceBuilder.length() > 7);
    }

    @Test
    public void testAsyncConditionallyFailureCompletionStage() {
        CompletableFuture<String> result = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1l, TimeUnit.SECONDS));

        CompletableFuture<String> exceptionHandler = result.handle((s, th) -> (th != null) ? "message cancel" : "");
        result.completeExceptionally(new RuntimeException("completed exceptionally"));
        Assertions.assertTrue(result.isCompletedExceptionally());

        try {
            result.join();
            Assertions.fail("Should have failed exceptionally");
        } catch (CompletionException ce) {
            Assertions.assertEquals("completed exceptionally", ce.getCause().getMessage());
        }

        Assertions.assertEquals("message cancel", exceptionHandler.join());
//        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
//                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//        CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
//        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//        Assertions.assertTrue(cf.isCompletedExceptionally());
//        try {
//            cf.join();
//            Assertions.fail("Should have thrown an exception");
//        } catch(CompletionException ex) { // just for testing
//            Assertions.assertEquals("completed exceptionally", ex.getCause().getMessage());
//        }
//        Assertions.assertEquals("message upon cancel", exceptionHandler.join());
    }


}
