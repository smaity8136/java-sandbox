package com.seedollar.sandbox.spring.web.rest.exception;

public class DuplicatePetException extends RuntimeException {

    public DuplicatePetException(String message) {
        super(message);
    }
}
