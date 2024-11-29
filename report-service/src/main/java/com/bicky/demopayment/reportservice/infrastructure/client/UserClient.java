package com.bicky.demopayment.reportservice.infrastructure.client;

import com.bicky.demopayment.reportservice.domain.entity.User;
import com.bicky.demopayment.reportservice.config.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/api/users/me")
    User me();
}
