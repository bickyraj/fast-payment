package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.jpa;

import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<PaymentModel, Long> {
}
