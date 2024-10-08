package com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
public class OrderRequestBody {
    private List<OrderItemPayLoad> orderItems;
    private Long paymentMethodId;
}
