package com.bicky.demopayment.orderservice.order.infrastructure.repository.postgres;

import com.bicky.demopayment.orderservice.order.domain.entity.User;
import com.bicky.demopayment.orderservice.order.domain.repository.UserRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaUserRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findByKeycloakId(String keycloakId) {
        return jpaUserRepository.findByKeycloakId(keycloakId).map(UserModel::toEntity).orElse(User.getEmptyObject());
    }
}
