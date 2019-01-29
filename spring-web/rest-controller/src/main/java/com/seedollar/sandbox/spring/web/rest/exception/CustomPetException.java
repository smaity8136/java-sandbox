package com.seedollar.sandbox.spring.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class CustomPetException extends RuntimeException {

    public CustomPetException(String message) {
        super(message);
    }
}
