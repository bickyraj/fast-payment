package com.bicky.demopayment.orderservice.order.application;

import com.bicky.demopayment.orderservice.order.annotation.CurrentUserIsOwner;
import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.service.OrderService;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final Long orderId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Order order;
        private final Boolean success;
    }

    private final OrderService orderService;

    public Response execute(Request request) {
        return Response.of(orderService.getOrder(request.orderId), true);
    }
}
