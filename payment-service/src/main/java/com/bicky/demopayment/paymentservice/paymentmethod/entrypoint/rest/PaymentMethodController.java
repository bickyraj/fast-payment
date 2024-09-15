package com.bicky.demopayment.paymentservice.paymentmethod.entrypoint.rest;

import com.bicky.demopayment.paymentservice.paymentmethod.application.GetAllPaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final GetAllPaymentMethodUseCase getAllPaymentMethodUseCase;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods () {
        GetAllPaymentMethodUseCase.Response response = getAllPaymentMethodUseCase.execute();
        return new ResponseEntity<>(response.getPaymentMethods(), HttpStatus.OK);
    }
}
