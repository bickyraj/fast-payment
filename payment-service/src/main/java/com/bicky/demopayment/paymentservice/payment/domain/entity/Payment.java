package com.bicky.demopayment.paymentservice.payment.domain.entity;

import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Payment {
    private Long id;
    private PaymentIntentID paymentIntentID;
    private PaymentMethod paymentMethod;
    private PaymentProvider provider;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double amount;
    private User user;

    public static Payment getEmptyObject() {
        return new Payment(null, null, null, null, null, null, null, null, null);
    }

    public boolean isEmpty() {
        return user == null;
    }
}
