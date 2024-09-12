package com.bicky.demopayment.orderpaymentservice.orderpayment.entrypoint.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderPaymentRequestBody {
    private Long orderId;
    private Long paymentMethodId;
}
