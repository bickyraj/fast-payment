package com.bicky.demopayment.orderservice.order.infrastructure.service;

import com.bicky.demopayment.orderservice.order.domain.entity.User;
import com.bicky.demopayment.orderservice.order.domain.repository.UserRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.client.UserClient;
import com.bicky.demopayment.orderservice.order.valueobject.KeycloakId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserClient userClient;

    public Optional<User> getCurrentUser() {
        KeycloakId keycloakId = KeycloakId.of(userClient.me().getKeycloakId());
        return Optional.of(userRepository.findByKeycloakId(keycloakId.getValue()));
    }
}
