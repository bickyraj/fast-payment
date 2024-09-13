package com.bicky.demopayment.paymentservice.payment.domain.repository;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;

public interface PaymentRepository {
    Payment create(Payment payment);
    Boolean updatePaymentStatus(PaymentIntentID paymentIntentID, PaymentStatus paymentStatus);
}
