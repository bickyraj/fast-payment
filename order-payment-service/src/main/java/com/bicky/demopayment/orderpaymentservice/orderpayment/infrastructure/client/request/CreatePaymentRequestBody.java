package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.request;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreatePaymentRequestBody {
    private Long paymentMethodId;
    private Double amount;
}
