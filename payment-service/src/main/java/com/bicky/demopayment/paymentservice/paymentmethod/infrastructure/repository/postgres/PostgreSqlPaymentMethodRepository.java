package com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.postgres;

import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.jpa.JpaPaymentMethodRepository;
import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.model.PaymentMethodModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentmethod.PostgreSqlPaymentMethodRepository")
@RequiredArgsConstructor
public class PostgreSqlPaymentMethodRepository implements PaymentMethodRepository {
    private final JpaPaymentMethodRepository jpaPaymentMethodRepository;

    @Override
    public List<PaymentMethod> getAllPaymentMethodsByUserId(Long userId) {
        return jpaPaymentMethodRepository.findAllByUserId(userId)
                .stream().map(PaymentMethodModel::toEntity)
                .toList();
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return jpaPaymentMethodRepository.findById(id).map(PaymentMethodModel::toEntity)
                .orElse(PaymentMethod.getEmptyObject());
    }
}
