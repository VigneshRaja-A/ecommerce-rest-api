package com.example.E_Commerce.exception;

public class ReviewAlreadyExistException extends RuntimeException {
    public ReviewAlreadyExistException(String message) {
        super(message);
    }
}
