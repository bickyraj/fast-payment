package com.bicky.demopayment.userservice.user.infrastructure.reporitory.postgres;

import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;
import com.bicky.demopayment.userservice.user.domain.repository.UserRepository;
import com.bicky.demopayment.userservice.user.infrastructure.reporitory.jpa.JpaUserRepository;
import com.bicky.demopayment.userservice.user.infrastructure.reporitory.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    @Override
    public boolean save(UserEntity user) {
        jpaUserRepository.save(UserModel.fromEntity(user));
        return true;
    }

    @Override
    public UserEntity findByKeycloakId(String keycloakId) {
        return jpaUserRepository.findByKeycloakId(keycloakId).map(UserModel::toEntity).orElse(UserEntity.getEmptyObject());
    }
}
