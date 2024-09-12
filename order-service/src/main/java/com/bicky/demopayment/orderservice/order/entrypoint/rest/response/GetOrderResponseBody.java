package com.bicky.demopayment.orderservice.order.entrypoint.rest.response;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class GetOrderResponseBody {
    private Long orderId;
    private Double totalAmount;
}
