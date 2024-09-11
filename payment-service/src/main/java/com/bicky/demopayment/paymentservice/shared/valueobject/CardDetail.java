package com.bicky.demopayment.paymentservice.shared.valueobject;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CardDetail {
    private Long cardNumber;
    private String cardHolderName;
    private Integer expiryMonth;
    private Integer expiryYear;
}
