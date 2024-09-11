package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.service.PaymentService;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.bicky.demopayment.paymentservice.stripe.StripeService;

public class PaymentServiceProvider implements PaymentService {
    private PaymentService paymentService;

    public PaymentServiceProvider(PaymentProvider paymentProvider) {
        if (paymentProvider.equals(PaymentProvider.STRIPE)) {
            this.paymentService = new StripeService();
        }
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    @Override
    public UserPaymentProvider createCustomer(User user) {
        return this.paymentService.createCustomer(user);
    }

    @Override
    public PaymentMethodId createPaymentMethod(AccountDetails accountDetails) {
        return this.paymentService.createPaymentMethod(accountDetails);
    }

    @Override
    public Boolean addPaymentMethod(String customerId, String paymentMethodId) {
        return this.paymentService.addPaymentMethod(customerId, paymentMethodId);
    }
}
