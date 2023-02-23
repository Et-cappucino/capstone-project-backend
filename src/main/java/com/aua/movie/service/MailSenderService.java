package com.aua.movie.service;

public interface MailSenderService {

    void send(String to, String email);

    String buildMail(String name, String link);
}
