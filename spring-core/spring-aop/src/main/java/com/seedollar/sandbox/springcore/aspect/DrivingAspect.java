package com.seedollar.sandbox.springcore.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class DrivingAspect {

    // Declare 'Before' advice
    @Before("execution(* com.seedollar.sandbox.springcore.domain.Car.drive(..))")
    public void beforeDrivingAdvice() {
        System.out.println("\n[BEFORE] DRIVING - Unlock car door");
        System.out.println("[BEFORE] DRIVING - Put seat-belt on");
        System.out.println("[BEFORE] DRIVING - Adjust mirrors");
        System.out.println("[BEFORE] DRIVING - Press 'START' Button to start engine");
    }

    // Declare 'After' advice
    @After("execution(* com.seedollar.sandbox.springcore.domain.Car.drive(..))")
    public void afterDrivingAdvice() {
        System.out.println("[AFTER] DRIVING - Press brake pedal");
        System.out.println("[AFTER] DRIVING - Pull handbrake up");

    }

    // Declare 'AfterReturning' advice
    @AfterReturning("execution(* com.seedollar.sandbox.springcore.domain.Car.drive(..))")
    public void afterReturningDrivingAdvice() {
        System.out.println("[AFTER RETURNING] DRIVING - Press 'START' Button to turn off engine");
    }

    // Declare 'AfterThrowing' advice
    @AfterThrowing("execution(* com.seedollar.sandbox.springcore.domain.Car.drive(..))")
    public void afterThrowingDrivingAdvice() {
        System.out.println("[AFTER THROWING] DRIVING - Put hazard lights on");
    }

    // Declare pointcut
    @Pointcut("execution(* com.seedollar.sandbox.springcore.domain.Car.reverse(..))")
    public void reverse() {
    }

    @Around("reverse()")
    public void duringReverse(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("\n[BEFORE] REVERSING - Unlock car door");
            System.out.println("[BEFORE] REVERSING - Put seat-belt on");
            System.out.println("[BEFORE] REVERSING - Adjust mirrors");
            System.out.println("[BEFORE] REVERSING - Press 'START' Button to start engine");
            proceedingJoinPoint.proceed();
            System.out.println("[AFTER] REVERSING - Press brake pedal");
            System.out.println("[AFTER] REVERSING - Pull handbrake up");
            System.out.println("[AFTER] REVERSING - Put into Park mode");
            System.out.println("[AFTER] REVERSING - Press 'START' Button to turn off engine");
        } catch (Throwable throwable) {
            System.out.println("[AFTER THROWING] REVERSING - Put hazard lights on");
        }
    }


}
