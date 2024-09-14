package com.bicky.demopayment.orderservice.order.domain.repository;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;

import java.util.List;

public interface OrderRepository {
    boolean create(Order order);
    Order findById(Long id);
    List<Order> findAllByUserId(Long userId);
}
