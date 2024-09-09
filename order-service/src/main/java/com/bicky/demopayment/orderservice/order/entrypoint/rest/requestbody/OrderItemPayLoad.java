package com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class OrderItemPayLoad {
    private Long productId;
    private Integer quantity;
}
