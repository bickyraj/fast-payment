package com.bicky.demopayment.productservice.product.infrastructure.repository.postgres;

import com.bicky.demopayment.productservice.product.annotations.CreateElasticProduct;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.jpa.JpaProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.repository.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgreSqlProductRepository implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @CreateElasticProduct
    @Override
    public Product save(Product product) {
        ProductModel productModel =  jpaProductRepository.save(ProductModel.fromEntity(product));
        return ProductModel.toEntity(productModel);
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        return jpaProductRepository.findAll(pageable).stream().map(ProductModel::toEntity)
                .collect(Collectors.toList());
    }
}
