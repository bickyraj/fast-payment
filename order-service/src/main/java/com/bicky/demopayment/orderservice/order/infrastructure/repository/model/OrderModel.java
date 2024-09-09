package com.bicky.demopayment.orderservice.order.infrastructure.repository.model;


import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItemModel> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = true)
    private UserModel user;

    @Column(nullable = false)
    private double totalPrice;

    public static OrderModel fromEntity(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());
        orderModel.setTotalPrice(order.getTotalPrice());
        orderModel.setOrderItems(order.getOrderItems().stream().map(OrderItemModel::fromEntity).toList());
        return orderModel;
    }

    public void addOrderItem(OrderItemModel orderItemModel) {
        orderItemModel.setOrder(this);
        this.orderItems.add(orderItemModel);
    }


    public static Order toEntity(OrderModel orderModel) {
        Order order = new Order();
        order.setId(orderModel.getId());
        order.setTotalPrice(orderModel.getTotalPrice());
        order.setOrderItems(orderModel.getOrderItems().stream().map(OrderItemModel::toEntity).toList());
        return order;
    }
}
