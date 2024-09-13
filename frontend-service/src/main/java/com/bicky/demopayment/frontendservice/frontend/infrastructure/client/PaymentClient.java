package com.bicky.demopayment.frontendservice.frontend.infrastructure.client;

import com.bicky.demopayment.frontendservice.frontend.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service", configuration = FeignConfig.class)
public interface PaymentClient {
//    @GetMapping("/api/payment-method/{paymentMethodId}")
//    ResponseEntity<GetPaymentMethodResponseBody> getPaymentMethod(@PathVariable Long paymentMethodId);
//
//    @PostMapping("/api/payment/create")
//    ResponseEntity<CreatePaymentResponseBody> createPayment(@RequestBody CreatePaymentRequestBody requestBody);
}
