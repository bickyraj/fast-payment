package com.bicky.demopayment.paymentservice.paymentmethod.domain.repository;


import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> getAllPaymentMethodsByUserId(Long userId);
    PaymentMethod getPaymentMethodById(Long id);
}
