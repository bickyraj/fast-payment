package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;

import java.util.Set;

public interface PaymentMethodRepository {
    boolean save(PaymentMethod paymentMethod);
    boolean existsBy(Long userId, PaymentProvider paymentProvider, String cardNumber, String expirationMonth, String expirationYear);
    Set<PaymentMethod> findByUserId(Long id);
    PaymentMethod findById(Long id);
}
