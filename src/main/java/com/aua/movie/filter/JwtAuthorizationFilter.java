package com.aua.movie.filter;

import com.aua.movie.config.JwtConfigurationProperties;
import com.aua.movie.exception.AccessDeniedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtConfigurationProperties configurationProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        boolean shouldBeIgnored = Stream.of("/api/login")
                .anyMatch(path -> request.getServletPath().contains(path));
        if (!shouldBeIgnored) {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader == null) {
                throw new AccessDeniedException("Access was denied because of wrong authorization");
            }
            if (authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(configurationProperties.getSecret().getBytes());
                UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token, algorithm);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token, Algorithm algorithm) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();
            List<SimpleGrantedAuthority> authorities = decodedJWT.getClaim("roles").asList(String.class)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(email, null, authorities);
        } catch (Exception exception) {
            throw new AccessDeniedException(exception.getMessage());
        }
    }
}
