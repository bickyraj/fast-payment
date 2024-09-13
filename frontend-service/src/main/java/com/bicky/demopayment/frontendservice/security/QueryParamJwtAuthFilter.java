package com.bicky.demopayment.frontendservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class QueryParamJwtAuthFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final Converter<Jwt, AbstractAuthenticationToken> authenticationConverter;

    public QueryParamJwtAuthFilter(JwtDecoder jwtDecoder, Converter<Jwt, AbstractAuthenticationToken> authenticationConverter) {
        this.jwtDecoder = jwtDecoder;
        this.authenticationConverter = authenticationConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getParameter("token");

        if (StringUtils.hasText(token)) {
            try {
                Jwt jwt = jwtDecoder.decode(token);

                // Extract roles from the jwt token
                Map<String, Object> realmAccess = jwt.getClaim("realm_access");
                if (realmAccess != null) {
                    List<String> roles = (List<String>) realmAccess.get("roles");
                    if (roles.contains("app_user")) {
                        var authentication = this.authenticationConverter.convert(jwt);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not have the app_user role");
                        return;
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not have the app_user role");
                    return;
                }

            } catch (Exception ex) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
