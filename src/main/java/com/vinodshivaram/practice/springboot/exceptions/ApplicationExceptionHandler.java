package com.vinodshivaram.practice.springboot.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApplicationExceptionHandler {
    protected Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        logger.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
