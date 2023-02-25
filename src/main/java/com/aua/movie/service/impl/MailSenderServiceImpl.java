package com.aua.movie.service.impl;

import com.aua.movie.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;
    private final ResourceLoader resourceLoader;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${email.confirmation.token.expiration.time.minutes}")
    private long expirationTime;

    @Value("${email.confirmation.token.link}")
    private String confirmationLinkBase;

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
    public String buildMail(String name, String token) {
        Resource resource = resourceLoader.getResource("classpath:static/view/confirmation-email.html");
        try {
            String content = new String(Files.readAllBytes(resource.getFile().toPath()));
            content = content.replace("$name", name);
            content = content.replace("$link", confirmationLinkBase + token);
            return content;
        } catch (IOException e) {
            return "";
        }
    }
}
