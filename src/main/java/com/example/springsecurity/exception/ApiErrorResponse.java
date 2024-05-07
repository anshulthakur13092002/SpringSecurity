package com.example.springsecurity.exception;

import java.util.Date;

public class ApiErrorResponse {
    private Date timestamp;
    private String message;


    public ApiErrorResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;

    }

    public ApiErrorResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
