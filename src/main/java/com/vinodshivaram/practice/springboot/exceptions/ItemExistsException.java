package com.vinodshivaram.practice.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemExistsException extends RuntimeException {
    public ItemExistsException(String message) {
        super(message);
    }
    public ItemExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
