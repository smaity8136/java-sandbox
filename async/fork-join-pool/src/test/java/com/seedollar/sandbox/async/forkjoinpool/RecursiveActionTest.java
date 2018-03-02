package com.seedollar.sandbox.async.forkjoinpool;

import com.google.common.collect.ImmutableList;
import com.seedollar.sandbox.async.forkjoinpool.task.SumAction;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RecursiveActionTest {

    @Test
    public void recursiveAction_NoForking_Test() {
        List<Long> data = ImmutableList.of(45l, 56l, 2l, 21l);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumAction action = new SumAction(data);
        forkJoinPool.invoke(action);
    }

    @Test
    public void recursiveAction_Forking_Test() {
        List<Long> data = ImmutableList.of(45l, 56l, 2l, 21l, 97l, 22l, 4l, 12l);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumAction action = new SumAction(data);
        forkJoinPool.invoke(action);
    }

    @Test
    public void recursiveActionPerformanceTest() {
        Random randomLongs = new Random();
        // Generate a random list of 1000 longs
        List<Long> data = randomLongs.longs(1000, 1, 100).boxed().collect(Collectors.toList());

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumAction action = new SumAction(data);
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        forkJoinPool.invoke(action);
        stopWatch1.stop();
        System.out.println("recusiveAction stopWatch (ms) = " + stopWatch1.getTotalTimeMillis());
    }


}
