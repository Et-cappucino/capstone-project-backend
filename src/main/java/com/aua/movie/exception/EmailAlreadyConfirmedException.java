package com.aua.movie.exception;

public class EmailAlreadyConfirmedException extends RuntimeException {

    public EmailAlreadyConfirmedException(String message) {
        super(message);
    }

    public EmailAlreadyConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }
}
