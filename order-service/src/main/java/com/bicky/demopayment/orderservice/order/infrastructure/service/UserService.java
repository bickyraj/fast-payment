package com.bicky.demopayment.orderservice.order.infrastructure.service;

import com.bicky.demopayment.orderservice.order.domain.entity.User;
import com.bicky.demopayment.orderservice.order.domain.repository.UserRepository;
import com.bicky.demopayment.orderservice.order.valueobject.KeycloakId;
import com.bicky.demopayment.orderservice.shared.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        KeycloakId keycloakId = KeycloakId.of(SecurityUtils.getCurrentUserId());
        return userRepository.findByKeycloakId(keycloakId.getValue());
    }
}
