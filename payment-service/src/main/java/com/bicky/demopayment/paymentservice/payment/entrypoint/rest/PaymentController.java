package com.bicky.demopayment.paymentservice.payment.entrypoint.rest;

import com.bicky.demopayment.paymentservice.payment.application.CreatePaymentUseCase;
import com.bicky.demopayment.paymentservice.payment.application.GetPaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.payment.application.SavePaymentMethodUseCase;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.CreatePaymentResponseBody;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.AddPaymentMethodRequestBody;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.response.CreatePayment;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.response.GetPaymentMethodResponseBody;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final SavePaymentMethodUseCase savePaymentMethodUseCase;
    private final GetPaymentMethodUseCase getPaymentMethodUseCase;
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

    @PostMapping("/add-payment-method")
    public Boolean savePaymentMethod(@RequestBody AddPaymentMethodRequestBody requestBody) {
        return savePaymentMethodUseCase.execute(SavePaymentMethodUseCase.Request.of(
                PaymentMethodId.of(requestBody.getPaymentMethodId()),
                PaymentProvider.valueOf(requestBody.getProvider())
        )).getSuccess();
    }

    @GetMapping("/payment-method/{paymentMethodId}")
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
