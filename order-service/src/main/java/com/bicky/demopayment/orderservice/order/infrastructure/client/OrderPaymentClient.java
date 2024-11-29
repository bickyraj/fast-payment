package com.bicky.demopayment.orderservice.order.infrastructure.client;


import com.bicky.demopayment.orderservice.config.FeignClientInterceptor;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.SaveOrderPaymentRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-payment-service", configuration = FeignClientInterceptor.class)
public interface OrderPaymentClient {
    @PostMapping("/api/order-payment")
    ResponseEntity<Boolean> saveOrderPayment(@RequestBody SaveOrderPaymentRequestBody requestBody);
}
