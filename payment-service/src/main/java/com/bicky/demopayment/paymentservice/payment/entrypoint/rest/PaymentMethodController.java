package com.bicky.demopayment.paymentservice.payment.entrypoint.rest;

import com.bicky.demopayment.paymentservice.payment.application.SavePaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.PaymentMethodRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {
    private final SavePaymentMethodUseCase savePaymentMethodUseCase;

    @PostMapping
    public Boolean savePaymentMethod(@RequestBody PaymentMethodRequestBody requestBody) {
        return savePaymentMethodUseCase.execute(SavePaymentMethodUseCase.Request.of(requestBody)).getSuccess();
    }
}
