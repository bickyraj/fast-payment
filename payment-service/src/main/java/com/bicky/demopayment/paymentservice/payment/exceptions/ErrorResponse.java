package com.bicky.demopayment.paymentservice.payment.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
    private String sqlState;
}
