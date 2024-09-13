package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.bicky.demopayment.paymentservice.shared.utils.SecurityUtils;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentCustomerId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserPaymentProviderRepository userPaymentProviderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    public Payment createPayment(Long paymentMethodId, Double amount) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId);
        String keycloakId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findByKeycloakId(keycloakId);
        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(paymentMethod.getPaymentProvider());
        UserPaymentProvider userPaymentProvider = userPaymentProviderRepository.getUserPaymentProviderBy(
                paymentMethod.getUser().getId(),
                paymentMethod.getPaymentProvider()
        );
        PaymentIntentID paymentIntentID = paymentServiceProvider.createPayment(
                PaymentCustomerId.of(userPaymentProvider.getProviderCustomerId()),
                PaymentMethodId.of(paymentMethod.getPaymentMethodId()),
                amount
        );
        Payment payment = new Payment();
        payment.setPaymentIntentID(paymentIntentID);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus(PaymentStatus.NA);
        payment.setProvider(paymentMethod.getPaymentProvider());
        payment.setUser(user);
        payment.setAmount(amount);
        return paymentRepository.create(payment);
    }
}
