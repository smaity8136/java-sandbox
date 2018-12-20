package com.seedollar.java.sandbox.threads.executors;

import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorsTest {

    @Test
    public void testScheduledExecutorService_schedule() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // We only schedule one task to be run after a delay of 1 second
        scheduledExecutorService.schedule(() -> {
            System.out.println(String.format("scheduled task running at [%s]...", LocalDateTime.now().toString()));
        }, 1, TimeUnit.SECONDS);

        Thread.sleep(2000);
    }

    @Test
    public void testScheduledExecutorService_scheduleAtFixedRate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(500);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // We schedule repeated tasks with the first task delayed for 50 milliseconds, and subsequent tasks are
        // repeatedly executed after a period of 100 milliseconds, with a maximum of 5 threads at a time.
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
            countDownLatch.countDown();
            System.out.println(String.format("fixed rate scheduled task with countdownlatch at: %d", countDownLatch.getCount()));
        }, 50, 100, TimeUnit.MILLISECONDS);

        countDownLatch.await(5000, TimeUnit.MILLISECONDS);
        scheduledFuture.cancel(true);
    }


}
