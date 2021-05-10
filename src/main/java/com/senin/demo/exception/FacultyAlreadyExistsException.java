package com.senin.demo.exception;

public class FacultyAlreadyExistsException extends RuntimeException {
    public FacultyAlreadyExistsException(String message) {
        super(message);
    }
}
