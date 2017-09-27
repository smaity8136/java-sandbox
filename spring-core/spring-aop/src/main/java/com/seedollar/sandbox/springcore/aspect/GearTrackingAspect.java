package com.seedollar.sandbox.springcore.aspect;

import com.google.common.collect.Maps;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;

@Aspect
public class GearTrackingAspect {

    Map<Integer, Integer> gearTrackingMap = Maps.newHashMap();

    //Define a pointcut for the changeGear() method
    @Pointcut("execution(* com.seedollar.sandbox.springcore.domain.Car.changeGear(int)) && args(gearNumber)")
    public void gearChanged(int gearNumber) {}

    @Before("gearChanged(gearNumber)")
    public void trackGearChange(int gearNumber) {
        if (gearTrackingMap.containsKey(gearNumber)) {
            gearTrackingMap.put(gearNumber, gearTrackingMap.get(gearNumber) + 1);
        } else {
            gearTrackingMap.put(gearNumber, 1);
        }
    }

    public String gearStat(int gearNumber) {
        return Try.of(() -> String.format("Gear Number: %d = %d", gearNumber, gearTrackingMap.get(gearNumber).intValue()))
                .getOrElse(String.format("No stats for gear number: %s", gearNumber));
    }
}
