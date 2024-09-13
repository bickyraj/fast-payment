package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.service.PaymentService;
import com.bicky.demopayment.paymentservice.shared.valueobject.*;
import com.bicky.demopayment.paymentservice.stripe.StripeService;
import lombok.Getter;

@Getter
public class PaymentServiceProvider implements PaymentService {
    private PaymentService paymentService;

    public PaymentServiceProvider(PaymentProvider paymentProvider) {
        if (paymentProvider.equals(PaymentProvider.STRIPE)) {
            this.paymentService = new StripeService();
        }
    }

    @Override
    public UserPaymentProvider createCustomer(User user) {
        return this.paymentService.createCustomer(user);
    }

    @Override
    public Boolean addPaymentMethod(String customerId, String paymentMethodId) {
        return this.paymentService.addPaymentMethod(customerId, paymentMethodId);
    }

    @Override
    public PaymentIntentID createPayment(PaymentCustomerId paymentCustomerId, PaymentMethodId paymentMethodId, Double amount) {
        return this.paymentService.createPayment(paymentCustomerId, paymentMethodId, amount);
    }

    @Override
    public CardDetail getCardDetail(PaymentMethodId paymentMethodId) {
        return this.paymentService.getCardDetail(paymentMethodId);
    }
}
