package com.bicky.demopayment.paymentservice.payment.entrypoint.rest;

import com.bicky.demopayment.paymentservice.payment.application.CreatePaymentUseCase;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.CreatePaymentResponseBody;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.response.CreatePayment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final CreatePaymentUseCase createPaymentUseCase;

    @PostMapping("/create")
    public ResponseEntity<CreatePayment> createPayment(@RequestBody CreatePaymentResponseBody requestBody) {
        CreatePaymentUseCase.Response response = createPaymentUseCase
                .execute(CreatePaymentUseCase.Request.of(requestBody.getPaymentMethodId(), requestBody.getAmount()));

        return new ResponseEntity<>(CreatePayment.of(
                response.getPaymentIntentId(),
                response.getSuccess()
        ), HttpStatus.OK);
    }
}
