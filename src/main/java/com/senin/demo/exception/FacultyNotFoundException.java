package com.senin.demo.exception;

public class FacultyNotFoundException extends RuntimeException{
    public FacultyNotFoundException(String message) {
        super(message);
    }
}
