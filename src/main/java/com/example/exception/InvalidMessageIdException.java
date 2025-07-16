package com.example.exception;

public class InvalidMessageIdException extends RuntimeException {
    public InvalidMessageIdException() {
        super("The message id is invalid.");
    } 
    public InvalidMessageIdException(String message) {
        super(message);
    }  
}
