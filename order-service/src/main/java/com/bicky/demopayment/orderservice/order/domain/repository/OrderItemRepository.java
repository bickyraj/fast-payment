package com.bicky.demopayment.orderservice.order.domain.repository;


import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    void save(OrderItem orderItem);
    void saveAll(List<OrderItem> orderItems);
}
