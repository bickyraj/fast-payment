package com.bicky.demopayment.paymentservice.payment.domain.entity;

import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodType;

@Setter
@Getter
@Builder
@ToString
public class PaymentMethod {
    private Long id;
    private User user;
    private PaymentProvider paymentProvider;
    private String paymentMethodId;
    private CardDetail cardDetail;
}
