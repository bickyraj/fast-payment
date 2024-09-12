package com.bicky.demopayment.paymentservice.shared.valueobject;

import lombok.Getter;

public class PaymentIntentID {
    private final String value;
    public PaymentIntentID(String value) {
        this.value = value;
    }

    public static PaymentIntentID of(String id) {
        return new PaymentIntentID(id);
    }

    public String getValue() {
        return value;
    }
}
