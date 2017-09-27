package com.seedollar.sandbox.springcore.aspect;

import com.seedollar.sandbox.springcore.domain.ParallelParking;
import com.seedollar.sandbox.springcore.domain.Parking;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ParkingIntroducer {

    @DeclareParents(value = "com.seedollar.sandbox.springcore.domain.Car+", defaultImpl = ParallelParking.class)
    public static Parking parking;

}
