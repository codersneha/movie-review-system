package com.example.helloWorld.exception;

public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
