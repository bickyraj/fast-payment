package com.bicky.demopayment.productservice.product.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private String description;
}
