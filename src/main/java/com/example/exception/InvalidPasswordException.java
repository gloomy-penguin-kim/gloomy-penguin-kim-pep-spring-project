package com.example.exception;

public class InvalidPasswordException extends RuntimeException { 
    public InvalidPasswordException() {
        super("The password is invalid");
    } 
    public InvalidPasswordException(String message) {
        super(message);
    } 
}