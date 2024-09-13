package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.postgres;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.Payment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.repository.PaymentRepository;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.jpa.JpaPaymentRepository;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model.PaymentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlPaymentRepository implements PaymentRepository {
    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    public Payment getById(Long id) {
        return jpaPaymentRepository.findById(id).map(PaymentModel::toEntity).orElse(null);
    }
}
