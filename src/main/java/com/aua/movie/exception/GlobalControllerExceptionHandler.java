package com.aua.movie.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionDetails);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(FORBIDDEN).body(exceptionDetails);
    }

    @ExceptionHandler(ConfirmationTokenNotFoundException.class)
    public ResponseEntity<Object> handleConfirmationTokenNotFoundException(ConfirmationTokenNotFoundException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(NOT_FOUND).body(exceptionDetails);
    }

    @ExceptionHandler(EmailAlreadyConfirmedException.class)
    public ResponseEntity<Object> handleEmailAlreadyConfirmedException(EmailAlreadyConfirmedException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(GONE).body(exceptionDetails);
    }

    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    public ResponseEntity<Object> handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(GONE).body(exceptionDetails);
    }
}
