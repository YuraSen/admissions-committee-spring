package com.senin.demo.exception;

public class ApplicantAlreadyExistsException extends RuntimeException{

    public ApplicantAlreadyExistsException(String message) {
        super(message);
    }
}
