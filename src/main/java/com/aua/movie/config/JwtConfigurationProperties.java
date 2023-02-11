package com.aua.movie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "token")
public class JwtConfigurationProperties {

    private String secret;

    private long expirationTime;

    private long refreshExpirationTime;
}
