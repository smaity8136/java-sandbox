package com.seedollar.sandbox.springcore.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Example exception using the @ResponseStatus annotation")
public class ResponseStatusExampleException extends RuntimeException {
}
