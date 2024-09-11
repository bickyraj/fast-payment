package com.bicky.demopayment.paymentservice.stripe;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.service.PaymentService;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
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
    public PaymentMethodId createPaymentMethod(AccountDetails accountDetails) {
        PaymentMethodCreateParams.CardDetails cardDetails = PaymentMethodCreateParams.CardDetails.builder()
                .setNumber(accountDetails.getCardNumber().toString())
                .setExpMonth((long) accountDetails.getExpiryMonth())
                .setExpYear((long) accountDetails.getExpiryYear())
                .setCvc(accountDetails.getCvc())
                .build();

        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(cardDetails)
                .setBillingDetails(PaymentMethodCreateParams.BillingDetails.builder()
                        .setName(accountDetails.getCardHolderName())
                        .build())
                .build();

        try {
            PaymentMethod stripePaymentMethod = PaymentMethod.create(params);
            return PaymentMethodId.of(stripePaymentMethod.getId());
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
}
