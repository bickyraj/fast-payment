package com.bicky.demopayment.paymentservice.payment.entrypoint.rest;

import com.bicky.demopayment.paymentservice.payment.application.GetPaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.payment.application.SavePaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.PaymentMethodRequestBody;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.response.GetPaymentMethodResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {
    private final SavePaymentMethodUseCase savePaymentMethodUseCase;
    private final GetPaymentMethodUseCase getPaymentMethodUseCase;

    @PostMapping
    public Boolean savePaymentMethod(@RequestBody PaymentMethodRequestBody requestBody) {
        return savePaymentMethodUseCase.execute(SavePaymentMethodUseCase.Request.of(requestBody)).getSuccess();
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<GetPaymentMethodResponseBody> getPaymentMethod(@PathVariable Long paymentMethodId) {
        GetPaymentMethodUseCase.Response response = getPaymentMethodUseCase.execute(
                GetPaymentMethodUseCase.Request.of(paymentMethodId)
        );
        return new ResponseEntity<>(GetPaymentMethodResponseBody.of(
                response.getPaymentMethod().getPaymentMethodId(),
                response.getPaymentMethod().getPaymentProvider().name()
        ), HttpStatus.OK);
    }
}
