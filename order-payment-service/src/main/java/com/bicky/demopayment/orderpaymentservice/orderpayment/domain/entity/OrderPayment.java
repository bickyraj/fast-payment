package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity;

import com.bicky.demopayment.orderpaymentservice.orderpayment.valueobject.PaymentStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@Setter
public class OrderPayment {
    private Long id;
    private Order order;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String stripePaymentIntentId;

    public static OrderPayment getEmptyObject() {
        return new OrderPayment(null, null, null, null, null);
    }

    public static boolean isEmptyObject(OrderPayment orderPayment) {
        return orderPayment.order == null;
    }
}
