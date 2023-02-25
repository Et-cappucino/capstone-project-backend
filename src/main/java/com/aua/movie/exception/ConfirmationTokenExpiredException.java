package com.aua.movie.exception;

public class ConfirmationTokenExpiredException extends RuntimeException {

    public ConfirmationTokenExpiredException(String message) {
        super(message);
    }

    public ConfirmationTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
