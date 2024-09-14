package com.bicky.demopayment.orderservice.order.infrastructure.repository.postgres;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.entity.OrderItem;
import com.bicky.demopayment.orderservice.order.domain.entity.User;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.domain.repository.ProductRepository;
import com.bicky.demopayment.orderservice.order.domain.repository.UserRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaOrderRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderItemModel;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderModel;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.ProductModel;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.UserModel;
import com.bicky.demopayment.orderservice.shared.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrderRepository implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;
    private final UserRepository userRepository;

    @Override
    public boolean create(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setTotalPrice(order.getTotalPrice());
        orderModel.setUser(UserModel.fromEntity(userRepository.findByKeycloakId(SecurityUtils.getCurrentUserId())));
        for (OrderItem orderItem: order.getOrderItems()) {
            OrderItemModel itemModel = new OrderItemModel();
            itemModel.setQuantity(orderItem.getQuantity());
            ProductModel productModel = ProductModel.fromEntity(orderItem.getProduct());
            itemModel.setProduct(productModel);
            orderModel.addOrderItem(itemModel);
        }
        jpaOrderRepository.save(orderModel);
        return true;
    }

    @Override
    public Order findById(Long id) {
        return jpaOrderRepository.findById(id).map(OrderModel::toEntity).orElse(Order.getEmptyObject());
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return jpaOrderRepository.findByUserId(userId).stream()
                .map(OrderModel::toEntity).toList();
    }
}
