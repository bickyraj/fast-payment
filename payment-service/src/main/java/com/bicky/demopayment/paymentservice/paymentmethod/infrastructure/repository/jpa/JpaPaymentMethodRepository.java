package com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.jpa;

import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.model.PaymentMethodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("paymentmethod.JpaPaymentMethodRepository")
public interface JpaPaymentMethodRepository extends JpaRepository<PaymentMethodModel, Long> {
    List<PaymentMethodModel> findAllByUserId(Long userId);
}
