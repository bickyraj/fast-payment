package com.bicky.demopayment.orderservice.order.entrypoint.rest;

import com.bicky.demopayment.orderservice.order.application.CreateOrderUseCase;
import com.bicky.demopayment.orderservice.order.application.GetOrderUseCase;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.GetAllOrdersResponseBody;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.OrderRequestBody;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.response.GetOrderResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    @GetMapping
    public ResponseEntity<GetAllOrdersResponseBody> getOrders() {
        return null;
    }

    @PostMapping("/create")
    public boolean createOrder(@RequestBody OrderRequestBody orderRequestBody) {
        return createOrderUseCase.execute(CreateOrderUseCase.Request.of(orderRequestBody)).getSuccess();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponseBody> getOrder(@PathVariable Long orderId) {
        GetOrderUseCase.Response response = getOrderUseCase.execute(
                GetOrderUseCase.Request.of(orderId)
        );
        return new ResponseEntity<>(
                GetOrderResponseBody.of(response.getOrder().getId(), response.getOrder().getTotalPrice()), HttpStatus.OK);
    }
}
