package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    public Optional<User> getUser() {
        return Optional.of(userClient.me());
    }
}
