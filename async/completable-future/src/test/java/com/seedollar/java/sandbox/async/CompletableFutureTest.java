package com.seedollar.java.sandbox.async;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CompletableFutureTest {

    private static ExecutorService service = Executors.newCachedThreadPool();

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

    @Test
    @DisplayName("Illustrates how you can join the results of 2 completable future tasks with a List of results")
    public void testCompletableFutureJoiningToCompletableFutureListResults() {
        CompletableFuture<List<String>> criminalNames = CompletableFuture.supplyAsync(() -> Lists.newArrayList("Venom", "The Joker", "Penguin"));
        CompletableFuture<List<String>> mobNames = CompletableFuture.supplyAsync(() -> Lists.newArrayList("Soprano", "O'reilley", "Carlione", "Jackson"));
        List<String> results = Stream.of(criminalNames, mobNames)
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        Assertions.assertEquals(7, results.size());
    }

    @Test
    @DisplayName("Illustrate how to use thenComposeAsync()")
    public void test_then_compose() throws Exception {

        Function<Integer, Supplier<List<Integer>>> getFirstTenMultiples = num ->
                () -> Stream.iterate(num, i -> i + num).limit(10).collect(Collectors.toList());

        Supplier<List<Integer>> multiplesSupplier = getFirstTenMultiples.apply(13);

        //Original CompletionStage
        CompletableFuture<List<Integer>> getMultiples = CompletableFuture.supplyAsync(multiplesSupplier, service);

        //Function that takes input from orignal CompletionStage
        Function<List<Integer>, CompletableFuture<Integer>> sumNumbers = multiples ->
                CompletableFuture.supplyAsync(() -> multiples.stream().mapToInt(Integer::intValue).sum());

        //The final CompletableFuture composed of previous two.
        CompletableFuture<Integer> summedMultiples = getMultiples.thenComposeAsync(sumNumbers, service);

        Assert.assertThat(summedMultiples.get(), CoreMatchers.is(715));
    }

    @Test
    @DisplayName("Illustrate how to use thenCombine(), allowing 2 seperate CompletionStage instances to finish, and then combine their results")
    public void testCombineWithBiFunction() {
        CompletableFuture<String> determineFirstName = CompletableFuture.supplyAsync(() -> "Judy");
        CompletableFuture<String> determineLastName = CompletableFuture.completedFuture("Greer");

        BinaryOperator<String> determineFullName = (f, l) -> String.format("%s %s", f, l);
        CompletableFuture<String> combined = determineFirstName.thenCombine(determineLastName, determineFullName);
        Assertions.assertEquals(combined.join(), "Judy Greer");
    }

    @Test
    @DisplayName("Illustrate how to use runAfterBothAsync(), where we allow 2 CompletionStage instances to complete, and then apply another Runnable afterwards")
    public void testRunAfterAsync() {
        List<String> results = new ArrayList<>();
        CompletableFuture<Void> addFirstNameTask = CompletableFuture.runAsync(() -> {
            pauseSeconds(2);
            results.add("Samuel");

        });
        CompletableFuture<Void> addLastNameTask = CompletableFuture.runAsync(() -> {
            pauseSeconds(3);
            results.add("Jackson");
        });

        CompletableFuture<Void> combineNamesTask = addFirstNameTask.runAfterBothAsync(addLastNameTask, () -> results.add(String.format("%s %s %s", results.get(0), "L.", results.get(1))));
        pauseSeconds(4);

        Assertions.assertEquals(3, results.size());
        Assertions.assertEquals("Samuel", results.get(0));
        Assertions.assertEquals("Jackson", results.get(1));
        Assertions.assertEquals("Samuel L. Jackson", results.get(2));
    }

    @Test
    @DisplayName("Illustrates how to use acceptEither(), which uses the first of 2 CompletionStages to finish")
    public void testAcceptEither() {
        CompletableFuture<String> runFirstTask = CompletableFuture.supplyAsync(() -> {
            pauseSeconds(2);
            pauseSeconds(2);
            return "FIRST";
        });

        CompletableFuture<String> runSecondTask = CompletableFuture.supplyAsync(() -> {
            pauseSeconds(1);
            return "SECOND";
        });

        List<String> printEntries = new ArrayList<>();
        // Use the completion stage that finishes first and add it's answer to the printEntries list.
        CompletableFuture<Void> printerTask = runFirstTask.acceptEither(runSecondTask, printEntries::add);

        pauseSeconds(3);
        Assertions.assertTrue(printerTask.isDone());
        Assertions.assertEquals(1, printEntries.size());
        Assertions.assertEquals("SECOND", printEntries.get(0));
    }

    @Test
    @DisplayName("Illusrate the runAfterEitherAsync() which is analogous to the acceptEither() where it executes a Runnable after the first of either completionStages complete.")
    public void testRunAfterEitherAsync() {
        List<String> result = new ArrayList<>();
        CompletableFuture<Void> firstTask = CompletableFuture.runAsync(() -> {
            pauseSeconds(3);
            result.add("FIRST");
        });

        CompletableFuture<Void> secondTask = CompletableFuture.runAsync(() -> {
            pauseSeconds(1);
            result.add("SECOND");
        });

        CompletableFuture<Void> showResultTask = firstTask.runAfterEitherAsync(secondTask, () -> result.add(0, result.get(0).toLowerCase()));
        pauseSeconds(4);
        Assertions.assertTrue(showResultTask.isDone());
        Assertions.assertEquals("second", result.get(0));
    }

    @Test
    @DisplayName("Illustrate applyToEither() which will apply a Function to the first result of either CompletionStages")
    public void testApplyToEither() {
        CompletableFuture<String> firstNameTask = CompletableFuture.supplyAsync(() -> {
            pauseSeconds(3);
            return "FIRST";
        });

        CompletableFuture<String> lastNameTask = CompletableFuture.supplyAsync(() -> {
            pauseSeconds(2);
            return "LAST";
        });

        CompletableFuture<Integer> countNameTask = firstNameTask.applyToEither(lastNameTask, String::length);
        Assertions.assertEquals(Integer.valueOf(4), countNameTask.join());
    }


    @Test
    @DisplayName("Illustration of allOf() which will wait for all CompletionStages to finish before evaluating further.")
    public void testAllOf() {
        List<String> entries = Lists.newArrayList();
        CompletableFuture<Void> allOfTask = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    entries.add("cs1");
                    pauseSeconds(2);
                }),
                CompletableFuture.runAsync(() -> {
                    entries.add("cs2");
                    pauseSeconds(3);
                }),
                CompletableFuture.runAsync(() -> {
                    entries.add("cs3");
                    pauseSeconds(4);
                })
        );

        allOfTask.join();

        Assertions.assertEquals(3, entries.size());
    }

    @Test
    @DisplayName("Illustration of anyOf() which expect the integer result of the first CompletionStage that is done.")
    public void testAnyOf() {
        CompletableFuture<Object> anyOfTask = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    pauseSeconds(2);
                    return 1024;
                }),
                CompletableFuture.supplyAsync(() -> {
                    pauseSeconds(1);
                    return 2048;
                })
        );

        // We expect the value to be 2048 because the second CompletionStage will finish first.
        Assertions.assertEquals(2048, anyOfTask.join());
    }


    @Test
    @DisplayName("Illustrate handle() to handle errors when any CompletionStage fails, or do something else if successful. handle() will always be called.")
    public void testHandle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringTask = CompletableFuture.supplyAsync(() -> "john marston");
        CompletableFuture<Integer> nameCountTask = stringTask.thenApply(name -> Character.toString(name.charAt(100)).length()); // Force exception
        CompletableFuture<Integer> isCountEvenTask = nameCountTask.thenApply(length -> length * 2);

        CompletableFuture<Integer> handle = isCountEvenTask.handle((res, ex) -> {
            if (res == null) {
                System.out.println("Handle Exception: " + ex.getMessage());
                log.info("Exception thrown", ex);
                return -5;
            } else {
                return 25;
            }
        });
        Assertions.assertEquals(Integer.valueOf(-5), handle.get());
    }

    @Test
    @DisplayName("Illustrate exceptionally(), which is allows us to handle the exception asynchronously")
    public void testExceptionally() {
        CompletableFuture<String> stringTask = CompletableFuture.supplyAsync(() -> "jim milton");
        CompletableFuture<Integer> nameCountTask = stringTask.thenApply(name -> Character.toString(name.charAt(100)).length()); // Force exception
        CompletableFuture<Integer> isCountEvenTask = nameCountTask.thenApply(length -> length * 2);

        CompletableFuture<Integer> handle = isCountEvenTask.exceptionally(ex -> {
            System.out.println("Failed Exceptionally: " + ex.getMessage());
            log.info("Exception thrown", ex);
            return -2;
        });
        Assertions.assertEquals(Integer.valueOf(-2), handle.join());
    }

    @Test
    @DisplayName("Illustrate thenAccept`")

    private static void pauseSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
