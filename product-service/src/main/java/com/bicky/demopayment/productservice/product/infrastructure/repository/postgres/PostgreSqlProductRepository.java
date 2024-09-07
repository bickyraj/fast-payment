package com.bicky.demopayment.productservice.product.infrastructure.repository.postgres;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.jpa.JpaProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    @Override
    public Product save(Product product) {
        ProductModel productModel =  jpaProductRepository.save(ProductModel.fromEntity(product));
        return ProductModel.toEntity(productModel);
    }
}
