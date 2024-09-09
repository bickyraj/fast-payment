package com.bicky.demopayment.orderservice.order.infrastructure.repository.model;

import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private ProductModel product;

    @Column
    private Integer quantity;

    public static OrderItemModel fromEntity (OrderItem orderItem) {
       OrderItemModel orderItemModel = new OrderItemModel();
       orderItemModel.setId(orderItem.getId());
       orderItemModel.setOrder(OrderModel.fromEntity(orderItem.getOrder()));
       orderItemModel.setProduct(ProductModel.fromEntity(orderItem.getProduct()));
       orderItemModel.setQuantity(orderItem.getQuantity());
       return orderItemModel;
    }

    public static OrderItem toEntity (OrderItemModel orderItemModel) {
        return OrderItem.builder()
                .id(orderItemModel.getId())
                .product(ProductModel.toEntity(orderItemModel.getProduct()))
                .quantity(orderItemModel.getQuantity())
                .build();
    }
}
