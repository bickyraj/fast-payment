package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;

public interface UserRepository {
    User findByKeycloakId(String keycloakId);
    User findByUserId(Long userId);
}
