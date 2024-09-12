package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.jpa;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model.OrderPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaOrderPaymentRepository extends JpaRepository<OrderPaymentModel, Long> {
    Optional<OrderPaymentModel> findByOrderIdAndPaymentMethodId(Long orderId, Long paymentMethodId);
}
