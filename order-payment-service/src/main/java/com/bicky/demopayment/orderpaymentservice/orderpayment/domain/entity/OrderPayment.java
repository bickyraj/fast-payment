package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity;

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
    private Payment payment;

    public static OrderPayment getEmptyObject() {
        return new OrderPayment(null, null, null, null);
    }

    public static boolean isEmptyObject(OrderPayment orderPayment) {
        return orderPayment.order == null;
    }
}
