package com.bicky.demopayment.orderservice.order.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Order {
    private Long id;
    private Long userId;
    private List<OrderItem> orderItems;
    private double totalPrice;
}
