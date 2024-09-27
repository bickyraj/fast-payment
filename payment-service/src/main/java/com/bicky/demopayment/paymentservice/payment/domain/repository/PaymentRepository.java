package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentModel;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;

import java.util.Optional;

public interface PaymentRepository {
    Payment create(Payment payment);
    Boolean updatePaymentStatus(PaymentIntentID paymentIntentID, PaymentStatus paymentStatus, Long eventCreatedAt);
    Optional<Payment> getPayment(PaymentIntentID paymentIntentID);
}
