package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.UserModel;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByKeycloakId(String keycloakId);
}
