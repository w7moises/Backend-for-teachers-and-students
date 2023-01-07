package com.tutofinder.app.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BookingException {

    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalServerErrorException(String code, String message, ErrorDto data) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
    }
}
