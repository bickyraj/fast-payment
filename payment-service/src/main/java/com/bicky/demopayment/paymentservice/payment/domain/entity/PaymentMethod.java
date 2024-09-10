package com.bicky.demopayment.paymentservice.payment.domain.entity;

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
    private PaymentMethodType paymentType;
    private PaymentProvider paymentProvider;
    private AccountDetails accountDetails;
}
