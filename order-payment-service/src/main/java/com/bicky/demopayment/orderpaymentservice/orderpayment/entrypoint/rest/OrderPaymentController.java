package com.bicky.demopayment.orderpaymentservice.orderpayment.entrypoint.rest;

import com.bicky.demopayment.orderpaymentservice.orderpayment.application.SaveOrderPaymentUseCase;
import com.bicky.demopayment.orderpaymentservice.orderpayment.entrypoint.rest.request.SaveOrderPaymentRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-payment")
public class OrderPaymentController {
    private final SaveOrderPaymentUseCase saveOrderPaymentUseCase;

    @PostMapping
    public ResponseEntity<Boolean> saveOrderPayment(@RequestBody SaveOrderPaymentRequestBody requestBody) {
        SaveOrderPaymentUseCase.Response response = saveOrderPaymentUseCase
                .execute(
                        SaveOrderPaymentUseCase.Request.of(
                                requestBody.getOrderId(),
                                requestBody.getPaymentMethodId()
                        )
                );
        return new ResponseEntity<>(response.isSuccess(), HttpStatus.OK);
    }

}
