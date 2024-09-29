package com.bicky.demopayment.sharedmodule.dto;

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
