package com.bicky.demopayment.paymentservice.shared.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.*;

public interface PaymentService {
    UserPaymentProvider createCustomer(User user);
    Boolean addPaymentMethod(String customerId, String paymentMethodId);
    PaymentIntentID createPayment(PaymentCustomerId paymentCustomerId, PaymentMethodId paymentMethodId, Double amount);
    CardDetail getCardDetail(PaymentMethodId paymentMethodId);
}
