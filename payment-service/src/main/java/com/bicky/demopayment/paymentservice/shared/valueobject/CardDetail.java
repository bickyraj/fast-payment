package com.bicky.demopayment.paymentservice.shared.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class CardDetail {
    private Long cardNumber;
    private String cardHolderName;
    private Integer expiryMonth;
    private Integer expiryYear;

    @JsonCreator
    public CardDetail(
            @JsonProperty("cardNumber") Long cardNumber,
            @JsonProperty("expiryYear") Integer expiryYear,
            @JsonProperty("expiryMonth") Integer expiryMonth,
            @JsonProperty("cardHolderName") String cardHolderName) {
        this.cardNumber = cardNumber;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.cardHolderName = cardHolderName;
    }
}
