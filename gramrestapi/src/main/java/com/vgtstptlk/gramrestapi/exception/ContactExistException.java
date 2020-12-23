package com.vgtstptlk.gramrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactExistException extends RuntimeException{
    public ContactExistException() {
        super("Contact has already created");
    }
}
