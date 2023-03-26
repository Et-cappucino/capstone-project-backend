package com.aua.movie.config;

import com.aua.movie.filter.JwtAuthenticationFilter;
import com.aua.movie.filter.JwtAuthorizationFilter;
import com.aua.movie.filter.JwtExceptionDelegatingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfigurationProperties jwtConfigurationProperties;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationProvider(), jwtConfigurationProperties);
        authenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/api/login/**", "/api/token/refresh/**", "/api/profile/confirm").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(new JwtAuthorizationFilter(jwtConfigurationProperties), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionDelegatingFilter(exceptionResolver), LogoutFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers(HttpMethod.GET, "/api/watchables/**")
                .antMatchers(HttpMethod.GET, "/api/actors/**")
                .antMatchers(HttpMethod.GET, "/api/comments/**")
                .antMatchers(HttpMethod.GET, "/api/cast/**")
                .antMatchers(HttpMethod.GET, "/api/filter/**")
                .antMatchers(HttpMethod.GET, "/api/search/**")
                .antMatchers(HttpMethod.GET, "/api/images/**")
                .antMatchers(HttpMethod.GET, "/api/recommend/**")
                .antMatchers(HttpMethod.POST, "/api/profiles")
                .antMatchers("/v2/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/webjars/**",
                "/swagger-ui.html",
                "/swagger-ui/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
