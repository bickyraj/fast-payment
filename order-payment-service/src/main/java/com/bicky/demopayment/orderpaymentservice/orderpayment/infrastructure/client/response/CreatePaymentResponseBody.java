package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreatePaymentResponseBody {
    private Long paymentId;
    private Boolean success;
}
