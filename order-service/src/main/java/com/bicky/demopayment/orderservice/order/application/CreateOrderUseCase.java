package com.bicky.demopayment.orderservice.order.application;

import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.entity.Product;
import com.bicky.demopayment.orderservice.order.domain.repository.ProductRepository;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.OrderItemPayLoad;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.OrderRequestBody;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.ProductModel;
import com.bicky.demopayment.orderservice.order.infrastructure.service.OrderService;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateOrderUseCase {

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final OrderRequestBody requestBody;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final OrderService orderService;
    private final ProductRepository productRepository;

    public Response execute(Request request) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemPayLoad orderItemPayLoad: request.getRequestBody().getOrderItems()) {
            Optional<Product> product = productRepository.findById(orderItemPayLoad.getProductId());
            if (product.isEmpty()) {
                return Response.of(false);
            }
            OrderItem orderItem = OrderItem.builder()
                    .product(product.get())
                    .quantity(orderItemPayLoad.getQuantity())
                    .build();
            orderItems.add(orderItem);
        }
        return Response.of(orderService.creatOrder(orderItems));
    }
}
