package com.bicky.demopayment.paymentservice.shared.valueobject;

public class PaymentCustomerId {
    private final String value;
    public PaymentCustomerId(String value) {
        this.value = value;
    }

    public static PaymentCustomerId of(String id) {
        return new PaymentCustomerId(id);
    }

    public String getValue() {
        return value;
    }
}
