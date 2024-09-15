package com.bicky.demopayment.orderservice.order.infrastructure.service;

import com.bicky.demopayment.orderservice.order.annotation.CurrentUserIsOwner;
import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.entrypoint.rest.requestbody.SaveOrderPaymentRequestBody;
import com.bicky.demopayment.orderservice.order.infrastructure.client.OrderPaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderPaymentClient orderPaymentClient;

    public Boolean creatOrder(List<OrderItem> orderItems, Long paymentMethodId) {
        double totalPrice = orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice())
                .sum();
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        Order createdOrder = orderRepository.create(order);
        return orderPaymentClient.saveOrderPayment(SaveOrderPaymentRequestBody.of(createdOrder.getId(), paymentMethodId)).getBody();
    }

    @CurrentUserIsOwner
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
