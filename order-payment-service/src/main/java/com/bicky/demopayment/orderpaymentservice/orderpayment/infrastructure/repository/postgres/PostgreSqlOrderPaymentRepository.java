package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.postgres;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.repository.OrderPaymentRepository;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.jpa.JpaOrderPaymentRepository;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model.OrderPaymentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrderPaymentRepository implements OrderPaymentRepository {
    private final JpaOrderPaymentRepository jpaOrderPaymentRepository;

    @Override
    public void save(OrderPayment orderPayment) {
        jpaOrderPaymentRepository.save(OrderPaymentModel.fromEntity(orderPayment));
    }

    @Override
    public OrderPayment findByOrderIdAndPaymentMethodId(Long orderId, Long paymentMethodId) {
        return jpaOrderPaymentRepository
                .findByOrderIdAndPaymentMethodId(orderId, paymentMethodId).map(OrderPaymentModel::toEntity)
                .orElse(OrderPayment.getEmptyObject());
    }
}
