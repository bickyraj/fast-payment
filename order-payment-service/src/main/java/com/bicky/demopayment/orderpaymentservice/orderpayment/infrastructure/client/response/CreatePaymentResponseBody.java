package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreatePaymentResponseBody {
    private String paymentIntentId;
    private Boolean success;
}
