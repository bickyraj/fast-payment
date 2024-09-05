package com.bicky.demopayment.orderservice.order.application;

import com.bicky.contracts.orderRequestBody.OrderRequestBodyOuterClass;
import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.valueobject.ProductID;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final OrderRequestBodyOuterClass.OrderRequestBody requestBody;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final OrderRepository orderRepository;

    public Response execute(Request request) {
        Order order = new Order();
        order.setQuantity(request.getRequestBody().getQuantity())
                        .setProductId(ProductID.of(request.getRequestBody().getProductId()));
        return Response.of(orderRepository.create(order));
    }
}
