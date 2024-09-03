package com.bicky.demopayment.userservice.user.domain.repository;


import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;

public interface UserRepository {
    boolean save(UserEntity user);
}
