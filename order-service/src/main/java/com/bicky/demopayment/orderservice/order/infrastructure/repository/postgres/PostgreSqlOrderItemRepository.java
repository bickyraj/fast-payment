package com.bicky.demopayment.orderservice.order.infrastructure.repository.postgres;

import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderItemRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaOrderItemRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrderItemRepository implements OrderItemRepository {
    private final JpaOrderItemRepository jpaOrderItemRepository;

    @Override
    public void save(OrderItem orderItem) {
        jpaOrderItemRepository.save(OrderItemModel.fromEntity(orderItem));

    }

    @Override
    public void saveAll(List<OrderItem> orderItems) {
        jpaOrderItemRepository.saveAll(orderItems.stream().map(OrderItemModel::fromEntity)
                .toList());
    }
}
