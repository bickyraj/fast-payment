package com.bicky.demopayment.paymentservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class QueryParamJwtAuthFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final Converter<Jwt, AbstractAuthenticationToken> authenticationConverter;

    public QueryParamJwtAuthFilter(JwtDecoder jwtDecoder, Converter<Jwt, AbstractAuthenticationToken> authenticationConverter) {
        this.jwtDecoder = jwtDecoder;
        this.authenticationConverter = authenticationConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter("token");

        if (token != null) {
            try {
                Jwt jwt = jwtDecoder.decode(token);
                AbstractAuthenticationToken authenticationToken = authenticationConverter.convert(jwt);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
