package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client;

import com.bicky.demopayment.orderpaymentservice.config.feign.FeignConfig;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/api/users/me")
    User me();
}
