package com.bicky.demopayment.orderservice.shared.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class SecurityUtils {

    public static Map<String, Object> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaims();
        }
        return null;
    }

    /**
     * Get the current Jwt token.
     * @return the Jwt token of the current user
     */
    public static Jwt getCurrentJwtToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            return (Jwt) authentication.getPrincipal();
        }

        return null;
    }

    public static String getCurrentUserId() {
        Jwt jwt = getCurrentJwtToken();

        if (jwt != null) {
            return jwt.getClaimAsString("sub");
        }

        return null;
    }
}
