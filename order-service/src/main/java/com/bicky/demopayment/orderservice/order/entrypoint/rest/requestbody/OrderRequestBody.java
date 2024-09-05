package com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequestBody {
    private Long productId;
    private Integer quantity;
}
