package com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity;

import com.bicky.demopayment.orderpaymentservice.orderpayment.valueobject.PaymentStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@ToString
@Setter
public class Payment {
    private Long id;
    private PaymentStatus status;
    private Double amount;

    public static Payment getEmptyObject() {
        return new Payment(null, null, null);
    }

    public static boolean isEmptyObject(Payment payment) {
        return payment.getStatus() == null;
    }
}
