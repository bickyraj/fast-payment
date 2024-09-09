package com.bicky.demopayment.orderservice.order.entrypoint.rest;

import com.bicky.contracts.orderRequestBody.OrderRequestBodyOuterClass;
import com.bicky.demopayment.orderservice.order.application.CreateOrderUseCase;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.OrderRequestBody;
import com.bicky.demopayment.orderservice.order.infrastructure.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping("/create")
    public boolean createOrder(@RequestBody OrderRequestBody orderRequestBody) {
        return createOrderUseCase.execute(CreateOrderUseCase.Request.of(orderRequestBody)).getSuccess();
    }
}
