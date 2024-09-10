package com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodType;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodRequestBody {
    private PaymentMethodType type;
    private PaymentProvider provider;
    private String cardHolderName;
    private Long cardNumber;
    private Integer expiryMonth;
    private Integer expiryYear;
}
