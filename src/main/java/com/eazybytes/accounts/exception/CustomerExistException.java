package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerExistException extends RuntimeException{
    private  String message;
    public CustomerExistException(String message) {
        super(message);
    }
}
