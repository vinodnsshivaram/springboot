package com.vinodshivaram.practice.springboot.exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vinodshivaram.practice.springboot.support.Utilities;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"status", "path", "method", "message", "timestamp"})
public class ErrorResponse {
    private HttpStatus status;
    private String path;
    private String timeStamp;
    private String message;
    private String method;

    public ErrorResponse(HttpStatus status, String path, String method, String message) {
        this.status = status;
        this.path = path;
        this.method = method;
        this.message = message;
        this.timeStamp = Utilities.getCurrentDateTimeFormatted();
    }

    public ErrorResponse(HttpStatus status, String path, String message) {
        this.status = status;
        this.path = path;
        this.method = method;
        this.message = message;
        this.timeStamp = Utilities.getCurrentDateTimeFormatted();
    }

    public ErrorResponse(HttpStatus status, String path) {
        this.status = status;
        this.path = path;
        this.timeStamp = Utilities.getCurrentDateTimeFormatted();
    }

    public ErrorResponse(HttpStatus status) {
        this.status = status;
        this.timeStamp = Utilities.getCurrentDateTimeFormatted();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
