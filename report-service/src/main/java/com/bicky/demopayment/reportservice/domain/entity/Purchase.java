package com.bicky.demopayment.reportservice.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Purchase {
    private String id;
    private Long userId;
    private double amount;
    private LocalDateTime createdAt;
}
