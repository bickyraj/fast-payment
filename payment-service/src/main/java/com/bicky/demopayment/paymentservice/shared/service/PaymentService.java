package com.bicky.demopayment.paymentservice.shared.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentCustomerId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;

public interface PaymentService {
    UserPaymentProvider createCustomer(User user);
    PaymentMethodId createPaymentMethod(AccountDetails accountDetails);
    Boolean addPaymentMethod(String customerId, String paymentMethodId);
    PaymentIntentID createPayment(PaymentCustomerId paymentCustomerId, PaymentMethodId paymentMethodId, Double amount);
}
