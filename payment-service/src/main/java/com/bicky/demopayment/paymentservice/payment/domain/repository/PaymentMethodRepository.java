package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Set;

public interface PaymentMethodRepository {
    void save(PaymentMethod paymentMethod) throws JsonProcessingException;
    Set<PaymentMethod> findByUserId(Long id);
}
