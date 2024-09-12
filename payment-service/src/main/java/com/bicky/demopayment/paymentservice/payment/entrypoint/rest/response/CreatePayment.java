package com.bicky.demopayment.paymentservice.payment.entrypoint.rest.response;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePayment {
    private String paymentIntentId;
    private Boolean success;
}
