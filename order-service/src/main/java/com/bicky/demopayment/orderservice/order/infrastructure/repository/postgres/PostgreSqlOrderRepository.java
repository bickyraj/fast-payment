package com.bicky.demopayment.orderservice.order.infrastructure.repository.postgres;

import com.bicky.demopayment.orderservice.order.domain.entity.Order;
import com.bicky.demopayment.orderservice.order.domain.entity.Product;
import com.bicky.demopayment.orderservice.order.domain.repository.OrderRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaOrderRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaProductRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.OrderModel;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOrderRepository implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;
    private final JpaProductRepository productRepository;

    @Override
    public boolean create(Order order) {
        Optional<ProductModel> productModel = productRepository.findById(order.getProductId().getProductId());
        if (productModel.isEmpty()) {
            return false;
        }
        OrderModel orderModel = new OrderModel();
        orderModel.setProduct(productModel.get());
        orderModel.setQuantity(order.getQuantity());
        jpaOrderRepository.save(orderModel);
        return true;
    }
}
