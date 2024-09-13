package com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPaymentMethodRequestBody {
    private String paymentMethodId;
    private String provider;
}
