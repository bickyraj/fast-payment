package com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreatePaymentRequestBody {
    private Long paymentMethodId;
    private Double amount;
}
