package com.bicky.demopayment.orderservice.order.domain.repository;

import com.bicky.demopayment.orderservice.order.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User findByKeycloakId(String keycloakId);
    Optional<User> findById(Long userId);
}
