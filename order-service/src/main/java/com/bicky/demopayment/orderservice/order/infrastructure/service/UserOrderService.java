package com.bicky.demopayment.orderservice.order.infrastructure.service;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private  final UserService userService;
    private final OrderRepository orderRepository;

    public List<Order> allOrders() {
        return orderRepository.findAllByUserId(userService.getCurrentUser().getId());
    }
}
