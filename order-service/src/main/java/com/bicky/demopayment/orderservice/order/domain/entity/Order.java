package com.bicky.demopayment.orderservice.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    private Long id;
    private Long userId;
    private List<OrderItem> orderItems;
    private double totalPrice;

    public static Order getEmptyObject() {
        return new Order(null, null, null, 0d);
    }
}
