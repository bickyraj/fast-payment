package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity;

import lombok.*;

@Builder
@ToString
@Getter
public class Order {
    private Long id;
    private double totalPrice;
}
