package com.seedollar.java.spring.webflux.backend.common.exception;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class OrchestratorExceptionWrapper extends DefaultErrorAttributes {

  private static final Logger logger = LoggerFactory.getLogger(OrchestratorExceptionWrapper.class);

  public OrchestratorExceptionWrapper() {
    super(true);
  }

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
    final Throwable error = getError(request);
    final Map<String, Object> errorAttributes = super.getErrorAttributes(request, false);
    if (error instanceof OrchestratorException) {
      logger.debug("Caught an instance of: {}, err: {}", OrchestratorException.class, error);
      final HttpStatus errorStatus = ((OrchestratorException) error).getStatus();
      final String correlationId = ((OrchestratorException) error).getCorrelationId();
      errorAttributes.replace(ErrorAttribute.STATUS.value, errorStatus.value());
      errorAttributes.replace(ErrorAttribute.ERROR.value, errorStatus.getReasonPhrase());
      errorAttributes.put(ErrorAttribute.CORRELATION_ID.value, correlationId);
      return errorAttributes;
    }
    return errorAttributes;
  }


  enum ErrorAttribute {
    STATUS("status"),
    ERROR("error"),
    CORRELATION_ID("correlationId");

    private final String value;

    ErrorAttribute(String value) {
      this.value = value;
    }
  }

}
