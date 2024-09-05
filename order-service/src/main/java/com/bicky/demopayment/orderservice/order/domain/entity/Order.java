package com.bicky.demopayment.orderservice.order.domain.entity;

import com.bicky.demopayment.orderservice.order.valueobject.ProductID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Order {
    private Long id;
    private ProductID productId;
    private Integer quantity;
}
