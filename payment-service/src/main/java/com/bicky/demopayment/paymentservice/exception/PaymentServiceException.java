package com.bicky.demopayment.paymentservice.exception;

public class PaymentServiceException extends RuntimeException {
    public PaymentServiceException(String message) {
        super(message);
    }
    public PaymentServiceException(String message, Throwable cause) {
            super(message, cause);
        }
}
