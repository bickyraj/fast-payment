package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.shared.utils.SecurityUtils;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final UserRepository userRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public boolean savePaymentMethod(PaymentMethod paymentMethod) {
        String userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findByKeycloakId(userId);
        paymentMethod.setUser(user);
        try {
            paymentMethodRepository.save(paymentMethod);
        } catch (JsonProcessingException e) {
            return false;
        }
        return true;
    }
}
