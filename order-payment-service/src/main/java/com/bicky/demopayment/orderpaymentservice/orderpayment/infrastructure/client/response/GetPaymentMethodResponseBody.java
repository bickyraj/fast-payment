package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class GetPaymentMethodResponseBody {
    private String providerPaymentMethodId;
    private String provider;
}
