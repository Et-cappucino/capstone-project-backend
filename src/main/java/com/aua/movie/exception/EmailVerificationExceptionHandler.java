package com.aua.movie.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class EmailVerificationExceptionHandler {

    @ExceptionHandler(EmailAlreadyConfirmedException.class)
    public ModelAndView handleEmailAlreadyConfirmedException(EmailAlreadyConfirmedException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("email-already-confirmed");
        return modelAndView;
    }

    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    public ModelAndView handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("confirmation-time-expired");
        return modelAndView;
    }

    @ExceptionHandler(ConfirmationTokenNotFoundException.class)
    public ModelAndView handleConfirmationTokenNotFoundException(ConfirmationTokenNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setViewName("confirmation-failed");
        return modelAndView;
    }
}
