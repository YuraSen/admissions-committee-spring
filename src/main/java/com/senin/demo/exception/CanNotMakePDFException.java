package com.senin.demo.exception;

public class CanNotMakePDFException extends RuntimeException {
    public CanNotMakePDFException(String message) {
        super(message);
    }
}
