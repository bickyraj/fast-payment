package com.bicky.demopayment.orderservice.order.domain.repository;

import com.bicky.demopayment.orderservice.order.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}
