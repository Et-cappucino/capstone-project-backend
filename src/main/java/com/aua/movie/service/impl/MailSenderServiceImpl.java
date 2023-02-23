package com.aua.movie.service.impl;

import com.aua.movie.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${email.confirmation.token.expiration.time.minutes}")
    private long expirationTime;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, UTF_8.name());
            messageHelper.setText(email, true);
            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm your email to activate your profile!");
            messageHelper.setFrom(from);
            mailSender.send(mimeMessage);
        } catch (MessagingException exception) {
            throw new IllegalStateException("Failed to send a mail");
        }
    }

    @Override
    public String buildMail(String name, String link) {
        return "<html><body><h1>Hi " + name + ",</h1>"
                + "<p>Thank you for signing up for our service. Please click the link below to confirm your account:</p>"
                + "<a style=\"background-color: #E53E3E; border: none; color: white; padding: 16px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 25px; margin: 4px 2px; cursor: pointer; border-radius: 13px;\" href="
                + link + ">" + "Confirm" + "</a>"
                + "</body></html>";
    }
}
