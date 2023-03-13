package com.aua.movie.service.impl;

import com.aua.movie.exception.ConfirmationTokenExpiredException;
import com.aua.movie.exception.ConfirmationTokenNotFoundException;
import com.aua.movie.exception.EmailAlreadyConfirmedException;
import com.aua.movie.model.EmailConfirmationToken;
import com.aua.movie.model.Profile;
import com.aua.movie.repository.EmailConfirmationTokenRepository;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.EmailConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@EnableScheduling
@Service
@RequiredArgsConstructor
public class EmailConfirmationTokenServiceImpl implements EmailConfirmationTokenService {

    private final ProfileRepository profileRepository;
    private final EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    @Value("${email.confirmation.token.expiration.time.minutes}")
    private long expirationTime;

    @Override
    public EmailConfirmationToken generateToken(Profile profile) {
        return new EmailConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(expirationTime),
                profile
        );
    }

    @Override
    public void saveEmailConfirmationToken(EmailConfirmationToken token) {
        emailConfirmationTokenRepository.save(token);
    }

    @Override
    public void confirmToken(String token) {
        EmailConfirmationToken confirmationToken = validateConfirmationToken(token);
        setConfirmedAt(token);
        enableProfile(confirmationToken.getProfile().getEmail());
    }

    private EmailConfirmationToken validateConfirmationToken(String token) {
        EmailConfirmationToken confirmationToken = emailConfirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ConfirmationTokenNotFoundException("Confirmation Token " + token + " was not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("Email has been already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenExpiredException("Confirmation Token " + token + " has already expired");
        }
        return confirmationToken;
    }

    private void setConfirmedAt(String token) {
        emailConfirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    private void enableProfile(String email) {
        profileRepository.enableProfile(email);
    }

    @Scheduled(initialDelayString = "${email.confirmation.token.cleanup.fixed.rate}", fixedRateString = "${email.confirmation.token.cleanup.fixed.rate}")
    private void cleanupUnconfirmedProfiles() {
        emailConfirmationTokenRepository.findByConfirmedAtIsNullAndExpiresAtBefore(LocalDateTime.now())
                .forEach(token -> {
                    emailConfirmationTokenRepository.delete(token);
                    profileRepository.delete(token.getProfile());
                });
    }
}
