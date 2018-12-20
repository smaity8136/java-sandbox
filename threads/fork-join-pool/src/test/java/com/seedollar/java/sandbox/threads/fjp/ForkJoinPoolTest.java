package com.seedollar.java.sandbox.threads.fjp;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    @Test
    public void test() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    }
}
