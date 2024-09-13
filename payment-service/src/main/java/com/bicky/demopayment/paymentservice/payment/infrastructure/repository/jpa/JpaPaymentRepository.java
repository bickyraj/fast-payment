package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa;

import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPaymentRepository extends JpaRepository<PaymentModel, Long> {
    Optional<PaymentModel> findByPaymentintentId(String id);
}
