package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client;

import com.bicky.demopayment.orderpaymentservice.config.feign.FeignConfig;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.request.CreatePaymentRequestBody;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response.CreatePaymentResponseBody;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response.GetPaymentMethodResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", configuration = FeignConfig.class)
public interface PaymentClient {
    @GetMapping("/api/payment-method/{paymentMethodId}")
    ResponseEntity<GetPaymentMethodResponseBody> getPaymentMethod(@PathVariable Long paymentMethodId);

    @PostMapping("/api/payment/create")
    ResponseEntity<CreatePaymentResponseBody> createPayment(@RequestBody CreatePaymentRequestBody requestBody);
}
