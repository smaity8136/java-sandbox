package com.seedollar.sandbox.junit5.exception;

/**
 * Created by seedollar on 7/12/17.
 */
public class CustomTestException extends Exception {

    public CustomTestException(Throwable e) {
        super(e);
    }

    public CustomTestException(String message) {
        super(message);
    }

    public CustomTestException(String message, Throwable ex) {
        super(message, ex);
    }
}
