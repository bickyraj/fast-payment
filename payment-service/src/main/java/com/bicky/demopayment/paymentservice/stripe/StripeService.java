package com.bicky.demopayment.paymentservice.stripe;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.service.PaymentService;
import com.bicky.demopayment.paymentservice.shared.valueobject.*;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeService implements PaymentService {
    @Override
    public UserPaymentProvider createCustomer(User user) {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setEmail(user.getEmail())
                .build();
        try {
            Customer customer = Customer.create(params);
            return UserPaymentProvider.builder()
                    .user(user)
                    .providerCustomerId(customer.getId())
                    .paymentProvider(PaymentProvider.STRIPE)
                    .build();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean addPaymentMethod(String customerId, String paymentMethodId) {
        try {
            PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);
            PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
                    .setCustomer(customerId)
                    .build();
            paymentMethod.attach(attachParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public PaymentIntentID createPayment(PaymentCustomerId paymentCustomerId, PaymentMethodId paymentMethodId, Double amount) {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100))
                .setCurrency("EUR")
                .setPaymentMethod(paymentMethodId.value())
                .setCustomer(paymentCustomerId.getValue())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.AUTOMATIC)
                .setConfirm(true)
                .build();

        try {
            return PaymentIntentID.of(PaymentIntent.create(params).getId());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CardDetail getCardDetail(PaymentMethodId paymentMethodId) {
        try {
            PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId.value());
            return CardDetail.builder()
                    .cardNumber(Long.valueOf(paymentMethod.getCard().getLast4()))
                    .cardHolderName(paymentMethod.getCustomer())
                    .expiryMonth(paymentMethod.getCard().getExpMonth().intValue())
                    .expiryYear(paymentMethod.getCard().getExpYear().intValue())
                    .build();

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
