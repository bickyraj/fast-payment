package com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa;

import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByUserId(Long userId);
}
