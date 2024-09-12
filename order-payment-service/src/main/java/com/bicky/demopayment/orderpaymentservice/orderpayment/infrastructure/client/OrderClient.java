package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client;

import com.bicky.demopayment.orderpaymentservice.config.feign.FeignConfig;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.response.GetOrderResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", configuration = FeignConfig.class)
public interface OrderClient {
    @GetMapping("/api/orders/{orderId}")
    ResponseEntity<GetOrderResponseBody> getOrder(@PathVariable Long orderId);
}
