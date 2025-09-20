package com.example.helloWorld.exception;

public class ReviewNotFoundException extends Exception {

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
