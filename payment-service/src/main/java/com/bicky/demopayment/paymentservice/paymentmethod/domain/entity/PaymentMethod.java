package com.bicky.demopayment.paymentservice.paymentmethod.domain.entity;

import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class PaymentMethod {
    private Long id;
    private User user;
    private String paymentMethodId;
    private CardDetail cardDetail;

    public static PaymentMethod getEmptyObject() {
        return new PaymentMethod( null, null, null, null);
    }
}
