package com.bicky.demopayment.orderservice.order.domain.repository;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;

public interface OrderRepository {
    boolean create(Order order);
    Order findById(Long id);
}
