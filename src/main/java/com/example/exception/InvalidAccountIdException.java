package com.example.exception;

public class InvalidAccountIdException extends RuntimeException {
    public InvalidAccountIdException() {
        super("The account id is invalid.");
    } 
    public InvalidAccountIdException(String message) {
        super(message);
    }  
}
