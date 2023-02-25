package com.aua.movie.service;

import com.aua.movie.model.EmailConfirmationToken;
import com.aua.movie.model.Profile;

public interface EmailConfirmationTokenService {

    EmailConfirmationToken generateToken(Profile user);

    void saveEmailConfirmationToken(EmailConfirmationToken token);

    void confirmToken(String token);
}
