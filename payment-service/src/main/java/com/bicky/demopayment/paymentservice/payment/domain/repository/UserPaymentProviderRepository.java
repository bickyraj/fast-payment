package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;

import java.util.Optional;

public interface UserPaymentProviderRepository {
    boolean create(UserPaymentProvider userPaymentProvider);
    UserPaymentProvider getUserPaymentProviderBy(Long userId, PaymentProvider paymentProvider);

}
