package com.bicky.demopayment.orderservice.order.application;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.infrastructure.service.UserOrderService;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserOrdersUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final List<Order> orders;
    }

    private final UserOrderService userOrderService;

    public Response execute() {
        return Response.of(userOrderService.allOrders());

    }
}
