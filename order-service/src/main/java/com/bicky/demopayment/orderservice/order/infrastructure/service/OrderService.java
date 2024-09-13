package com.bicky.demopayment.orderservice.order.infrastructure.service;

import com.bicky.demopayment.orderservice.order.annotation.CurrentUserIsOwner;
import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.domain.repository.UserRepository;
import com.bicky.demopayment.orderservice.shared.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public boolean creatOrder(List<OrderItem> orderItems) {
        double totalPrice = orderItems.stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice())
                .sum();
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return orderRepository.create(order);
    }

    @CurrentUserIsOwner
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
