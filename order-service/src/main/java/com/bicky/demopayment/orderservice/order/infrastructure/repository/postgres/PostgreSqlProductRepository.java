package com.bicky.demopayment.orderservice.order.infrastructure.repository.postgres;

import com.bicky.demopayment.orderservice.order.domain.entity.Product;
import com.bicky.demopayment.orderservice.order.domain.repository.ProductRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.jpa.JpaProductRepository;
import com.bicky.demopayment.orderservice.order.infrastructure.repository.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgreSqlProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id).map(ProductModel::toEntity);
    }
}
