package com.bicky.demopayment.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {
    @GetMapping("/gateway/health")
    public String healthCheck() {
        return "Gateway running";
    }
}
