package com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final UserClient userClient;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.getAllPaymentMethodsByUserId(userClient.me().getId());
    }
}
