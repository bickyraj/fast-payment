package com.bicky.demopayment.notificationservice.shared.valueobject;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private Long userId;
    private LocalDateTime createdDate;
    private double totalAmount;
}
