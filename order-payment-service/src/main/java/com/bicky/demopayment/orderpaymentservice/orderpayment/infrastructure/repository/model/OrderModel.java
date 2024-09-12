package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double totalPrice;

    public static OrderModel fromEntity(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());
        orderModel.setTotalPrice(order.getTotalPrice());
        return orderModel;
    }

    public static Order toEntity(OrderModel orderModel) {
        return Order.builder().id(orderModel.getId()).totalPrice(orderModel.getTotalPrice()).build();
    }
}
