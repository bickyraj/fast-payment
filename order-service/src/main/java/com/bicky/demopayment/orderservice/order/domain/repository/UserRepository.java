package com.bicky.demopayment.orderservice.order.domain.repository;

import com.bicky.demopayment.orderservice.order.domain.entity.User;

public interface UserRepository {
    User findByKeycloakId(String keycloakId);
}
