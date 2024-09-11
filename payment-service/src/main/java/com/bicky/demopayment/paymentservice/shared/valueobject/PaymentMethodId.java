package com.bicky.demopayment.paymentservice.shared.valueobject;

public class PaymentMethodId {
    private final String id;
    public PaymentMethodId(String id) {
        this.id = id;
    }

    public static PaymentMethodId of(String id) {
        return new PaymentMethodId(id);
    }

    public String value() {
        return id;
    }
}
