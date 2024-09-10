package com.bicky.demopayment.paymentservice.shared.valueobject;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AccountDetails {
    private Long cardNumber;
    private String cardHolderName;
    private Integer expiryMonth;
    private Integer expiryYear;
}
