package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.repository;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;

public interface OrderPaymentRepository {
    void save(OrderPayment orderPayment);
    OrderPayment findByOrderIdAndPaymentMethodId(Long orderId, Long paymentMethodId);
}
