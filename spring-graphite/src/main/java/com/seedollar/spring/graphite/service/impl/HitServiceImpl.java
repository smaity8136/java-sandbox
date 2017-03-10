package com.seedollar.spring.graphite.service.impl;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import com.seedollar.spring.graphite.service.HitService;
import org.springframework.stereotype.Service;

/**
 * Created by seedollar on 3/10/17.
 */
@Service
public class HitServiceImpl implements HitService {

    private Meter hitMeter;
    private Counter hitCounter;
    private Histogram hitRateHistogram;
    private Timer hitEvenIntervalTimer;

    public HitServiceImpl(Meter hitMeter, Counter hitCounter, Histogram hitRateHistogram, Timer hitEvenIntervalTimer) {
        this.hitMeter = hitMeter;
        this.hitCounter = hitCounter;
        this.hitRateHistogram = hitRateHistogram;
        this.hitEvenIntervalTimer = hitEvenIntervalTimer;
    }

    @Override
    public void whack() {
        hitCounter.inc();
        hitMeter.mark();
        hitRateHistogram.update(hitCounter.getCount());
        timeEvenIntervalFunction.apply(hitEvenIntervalTimer);
    }

    @Override
    public void recover() {
        hitCounter.dec();
        hitRateHistogram.update(hitCounter.getCount());
        timeEvenIntervalFunction.apply(hitEvenIntervalTimer);
    }

}
