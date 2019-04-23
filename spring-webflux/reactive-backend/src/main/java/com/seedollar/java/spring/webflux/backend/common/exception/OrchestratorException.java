package com.seedollar.java.spring.webflux.backend.common.exception;

import org.springframework.http.HttpStatus;

public class OrchestratorException extends RuntimeException {

  private String correlationId;
  private final HttpStatus status;

  public OrchestratorException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public OrchestratorException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }
}
