package com.seedollar.spring.graphite.service;

import com.codahale.metrics.Timer;
import javaslang.Function1;
import javaslang.control.Try;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seedollar on 3/10/17.
 */
public interface HitService {

    AtomicInteger globalHitCounter = new AtomicInteger(0);

    Function1<Timer, Boolean> timeEvenIntervalFunction = (timer) -> {
        Timer.Context stopwatch = timer.time();
        Try.of(() -> {
            Thread.sleep(new Long(new Random().nextInt(5) * 100));
            return true;
        }).andThen(() -> stopwatch.stop());
       return true;
    };

    void whack();

    void recover();
}
