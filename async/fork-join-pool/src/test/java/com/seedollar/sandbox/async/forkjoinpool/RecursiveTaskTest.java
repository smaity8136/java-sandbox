package com.seedollar.sandbox.async.forkjoinpool;

import com.google.common.collect.ImmutableList;
import com.seedollar.sandbox.async.forkjoinpool.task.SumAction;
import com.seedollar.sandbox.async.forkjoinpool.task.SumTask;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class RecursiveTaskTest {

    @Test
    public void recursiveTask_NoForking_Test() {
        List<Long> data = ImmutableList.of(45l, 56l, 2l, 21l);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        Long total = forkJoinPool.invoke(task);
        System.out.println("total = " + total);
    }

    @Test
    public void recursiveTask_Forking_Test() {
        List<Long> data = ImmutableList.of(45l, 56l, 2l, 21l, 97l, 22l, 4l, 12l);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        Long total = forkJoinPool.invoke(task);
        System.out.println("total = " + total);
    }

    @Test
    public void recursiveTaskPerformanceTest() {
        Random randomLongs = new Random();
        // Generate a random list of 1000 longs
        List<Long> data = randomLongs.longs(1_000_000, 1, 100).boxed().collect(Collectors.toList());

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        System.out.println("Recursive Total: " + forkJoinPool.invoke(task));
        stopWatch1.stop();
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        System.out.println("Non-Recursive Total: " + data.stream().reduce(0l, Long::sum));
        stopWatch2.stop();
        System.out.println("recursive computation (ms) = " + stopWatch1.getTotalTimeMillis());
        System.out.println("normal computation (ms) = " + stopWatch2.getTotalTimeMillis());
    }

}
