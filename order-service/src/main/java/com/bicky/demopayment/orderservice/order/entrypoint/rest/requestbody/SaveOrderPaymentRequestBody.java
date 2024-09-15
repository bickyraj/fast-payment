package com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SaveOrderPaymentRequestBody {
    private Long orderId;
    private Long paymentMethodId;
}
