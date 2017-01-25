package com.seedollar.java.sandbox.futures.completable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seedollar on 1/24/17.
 */
public class CompletableFutureTest {

    static AtomicBoolean invocationFlag;
    static AtomicInteger publishCounter;

    @BeforeEach
    void initBeforeEach() {
        invocationFlag = new AtomicBoolean(false);
        publishCounter = new AtomicInteger(0);

    }

    @Test
    @DisplayName("This test shows how to implement the CompletableFuture")
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::sendMessage);
        Assertions.assertEquals("Hello CompletableFuture!", completableFuture.get());
    }

    @Test
    @DisplayName("A CompletableFuture with a callback invocation")
    public void testCompletableFuture_WithCallbacks() throws ExecutionException, InterruptedException {
        Assertions.assertFalse(invocationFlag.get());
        CompletableFuture.supplyAsync(this::sendMessage)
                .thenApply(this::decorateMessage) // Callback 1
                .thenAccept(this::publishMessage); // Callback 2

        // Give some delay so the above can complete
        Thread.sleep(2500);
        Assertions.assertTrue(invocationFlag.get());
    }

    @Test
    @DisplayName("A CompletableFuture which composes another async process, effectively flattening the process")
    public void testCompletableFuture_Compose() throws InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::sendMessage)
                .thenCompose(this::sendMessageAsync); // The compose creates a flattening effect

        Thread.sleep(4200); // Wait longer than 2 invocations of sendMessage()
        Assertions.assertEquals(2, publishCounter.get());
    }

    @Test
    @DisplayName("A CompletableFuture which handles an exception in order to fail/invoke a different function when things go wrong using the exceptionally() method")
    public void testCompletableFuture_Exceptionally() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::sendMessageAndFail)
                .exceptionally(ex -> "Something failed"); // Fail gracefully in-case something goes wrong in the execution before.

        Thread.sleep(2200);
        Assertions.assertEquals("Something failed", completableFuture.get());
    }

    private String sendMessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello CompletableFuture! - " + publishCounter.incrementAndGet();
    }

    private String decorateMessage(String message) {
        return "[--" + message + "--]";
    }

    private void publishMessage(String message) {
        invocationFlag.set(true);
        System.out.println("publishMessage = " + message);

    }

    private CompletionStage<String> sendMessageAsync(String message) {
        return CompletableFuture.supplyAsync(this::sendMessage);
    }

    private String sendMessageAndFail() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Mock failure");
    }
}
