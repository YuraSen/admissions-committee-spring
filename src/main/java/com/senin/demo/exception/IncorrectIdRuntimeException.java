package com.senin.demo.exception;

public class IncorrectIdRuntimeException extends RuntimeException {
    public IncorrectIdRuntimeException() {
    }

    public IncorrectIdRuntimeException(String message) {
        super(message);
    }

    public IncorrectIdRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectIdRuntimeException(String idPositive, String idEmpty) {
    }
}

