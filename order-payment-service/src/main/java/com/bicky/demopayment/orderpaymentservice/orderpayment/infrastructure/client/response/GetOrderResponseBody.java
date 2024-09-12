package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class GetOrderResponseBody {
    private Long orderId;
    private Double totalAmount;
}
