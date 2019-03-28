package com.seedollar.java.spring.webflux.backend.common.exception;

import org.springframework.http.HttpStatus;

public class OrchestratorException extends RuntimeException {

  private final HttpStatus status;

  OrchestratorException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  OrchestratorException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

}
