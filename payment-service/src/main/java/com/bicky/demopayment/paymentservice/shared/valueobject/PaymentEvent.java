package com.bicky.demopayment.paymentservice.shared.valueobject;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PaymentEvent {
    private Long userId;
    private LocalDateTime createdDate;
    private double totalAmount;
}
