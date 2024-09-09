package com.bicky.demopayment.orderservice.order.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class OrderItem {
    private Long id;
    private Order order;
    private Product product;
    private Integer quantity;
}
