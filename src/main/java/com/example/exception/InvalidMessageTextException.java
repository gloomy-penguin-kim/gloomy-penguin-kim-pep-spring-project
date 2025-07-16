package com.example.exception;

public class InvalidMessageTextException extends RuntimeException {
    public InvalidMessageTextException() {
        super("The message text is invalid.");
    } 
    public InvalidMessageTextException(String message) {
        super(message);
    }  
}
