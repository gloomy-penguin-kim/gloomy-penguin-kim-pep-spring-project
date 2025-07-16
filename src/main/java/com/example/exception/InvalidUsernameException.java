package com.example.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("The username is invalid.");
    }
    public InvalidUsernameException(String message) {
        super(message);
    } 
}