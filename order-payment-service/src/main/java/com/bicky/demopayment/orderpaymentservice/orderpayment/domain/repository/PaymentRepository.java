package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.repository;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.Payment;

public interface PaymentRepository {
    Payment getById(Long id);
}
