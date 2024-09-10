package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.postgres;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa.JpaUserRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.UserModel;

@Repository
@RequiredArgsConstructor
public class PostgreSqlUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findByKeycloakId(String keycloakId) {
        return jpaUserRepository.findByKeycloakId(keycloakId).map(UserModel::toEntity).orElse(User.getEmptyObject());
    }
}
