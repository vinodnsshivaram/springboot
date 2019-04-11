package com.vinodshivaram.practice.springboot.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class ApplicationExceptionHandler {
    protected Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, request.getRequestURI(), request.getMethod(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ItemExistsException.class})
    public ResponseEntity<ErrorResponse> handleItemExistsException(ItemExistsException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, request.getRequestURI(), request.getMethod(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
