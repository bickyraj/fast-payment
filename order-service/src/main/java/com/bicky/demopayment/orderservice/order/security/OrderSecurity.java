package com.bicky.demopayment.orderservice.order.security;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSecurity {
    private final OrderRepository orderRepository;

    public boolean isOrderOwner(Authentication authentication, Long orderId) {
        String currentUsername = authentication.getName();
        Order order = orderRepository.findById(orderId);
        return order != null && currentUsername.equals(order.getUser().getKeycloakId());
    }
}
