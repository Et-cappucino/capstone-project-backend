package com.aua.movie.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ExceptionDetails {

    private String message;

    private String exceptionName;

    private ZonedDateTime timeStamp;
}
