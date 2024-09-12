package com.bicky.demopayment.orderservice.order.application;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
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

    private final OrderRepository orderRepository;

    public Response execute(Request request) {
        return Response.of(orderRepository.findById(request.orderId), true);
    }
}
