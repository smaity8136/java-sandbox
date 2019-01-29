package com.seedollar.sandbox.spring.web.rest.advice;

import com.seedollar.sandbox.spring.web.rest.exception.DuplicatePetException;
import com.seedollar.sandbox.spring.web.rest.exception.PetException;
import com.seedollar.sandbox.spring.web.rest.exception.PetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PetErrorAdvice {

    @ExceptionHandler(value = {DuplicatePetException.class, PetException.class})
    public ResponseEntity<String> handleDuplicatePetException(DuplicatePetException duplicatePetException) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, duplicatePetException);
    }

    @ExceptionHandler(value = PetNotFoundException.class)
    public ResponseEntity<String> handlePetNotFoundException(PetNotFoundException petNotFoundException) {
        return error(HttpStatus.NOT_FOUND, petNotFoundException);
    }

    private ResponseEntity<String> error(HttpStatus status, Throwable e) {
        log.error("Error Occurred: " + e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
