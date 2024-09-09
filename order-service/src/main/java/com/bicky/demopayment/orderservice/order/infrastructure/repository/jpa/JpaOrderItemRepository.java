package com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa;


import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderItemRepository extends JpaRepository<OrderItemModel, Long> {
}
