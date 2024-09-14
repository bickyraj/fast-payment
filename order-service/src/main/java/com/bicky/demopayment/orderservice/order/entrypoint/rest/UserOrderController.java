package com.bicky.demopayment.orderservice.order.entrypoint.rest;

import com.bicky.demopayment.orderservice.order.application.GetUserOrdersUseCase;
import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.response.GetUserOrderResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders/user-order")
public class UserOrderController {
    private final GetUserOrdersUseCase getUserOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<GetUserOrderResponseBody>> getAllOrders() {
        GetUserOrdersUseCase.Response response = getUserOrdersUseCase.execute();
        return new ResponseEntity<>(
                response.getOrders().stream()
                .map(order -> GetUserOrderResponseBody.of(
                        order.getId(),
                        order.getOrderItems().stream().map(orderItem -> GetUserOrderResponseBody
                                .ResponseOrderItem.of(orderItem.getId(),
                                orderItem.getProduct())).toList()
                )).toList(), HttpStatus.OK);
    }
}
