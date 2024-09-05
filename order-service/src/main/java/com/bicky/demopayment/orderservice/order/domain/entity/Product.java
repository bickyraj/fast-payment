package com.bicky.demopayment.orderservice.order.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private Long id;
    private String name;
    private Double price;
}
