package com.senin.demo.exception;

public class IncorrectUserNameException extends RuntimeException {
    public IncorrectUserNameException(String message) {
        super(message);
    }
}
