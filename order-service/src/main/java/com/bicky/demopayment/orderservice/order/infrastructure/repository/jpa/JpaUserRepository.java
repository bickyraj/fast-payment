package com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa;

import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByKeycloakId(String keycloakId);
}
