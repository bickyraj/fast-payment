package com.bicky.demopayment.userservice.user.infrastructure.reporitory.jpa;

import com.bicky.demopayment.userservice.user.infrastructure.reporitory.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByKeycloakId(String keycloakId);
}
