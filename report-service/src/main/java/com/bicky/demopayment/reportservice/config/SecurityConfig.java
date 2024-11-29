package com.bicky.demopayment.reportservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequest -> {
            authorizeRequest.anyRequest().authenticated();
        }).oauth2ResourceServer(oauth2ResourceServer -> {
            oauth2ResourceServer.jwt(jwt -> {
                jwt.jwkSetUri(jwkSetUri);
            });
        }).build();
    }
}
