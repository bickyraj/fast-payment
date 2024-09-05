package com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa;

import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductModel, Long> {
}
