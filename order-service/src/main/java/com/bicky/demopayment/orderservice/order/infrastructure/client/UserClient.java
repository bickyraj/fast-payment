package com.bicky.demopayment.orderservice.order.infrastructure.client;

import com.bicky.demopayment.orderservice.config.FeignConfig;
import com.bicky.demopayment.orderservice.order.valueobject.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", configuration = FeignConfig.class, url = "http://localhost:3030")
public interface UserClient {

    @GetMapping("/api/users/me")
    User me();
}
