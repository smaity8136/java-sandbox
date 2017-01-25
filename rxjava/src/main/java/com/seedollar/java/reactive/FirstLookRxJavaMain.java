package com.seedollar.java.reactive;

import io.reactivex.Flowable;

/**
 * Created by seedollar on 1/19/17.
 */
public class FirstLookRxJavaMain {

    public static void main(String[] args) {
        Flowable.just("Print me").subscribe(System.out::print);
    }
}
