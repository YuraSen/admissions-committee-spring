package com.senin.demo.exception;

public class RequestAlreadyExistsException  extends RuntimeException{
    public RequestAlreadyExistsException(String message) {
        super(message);
    }
}
