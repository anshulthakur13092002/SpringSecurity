package com.example.springsecurity.exception;



public class CustomValidationException extends RuntimeException {
    public CustomValidationException() {
    }

    public CustomValidationException(String message) {
        super(message);
    }
}
