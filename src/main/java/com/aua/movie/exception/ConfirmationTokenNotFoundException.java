package com.aua.movie.exception;

public class ConfirmationTokenNotFoundException extends RuntimeException {

    public ConfirmationTokenNotFoundException(String message) {
        super(message);
    }

    public ConfirmationTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
