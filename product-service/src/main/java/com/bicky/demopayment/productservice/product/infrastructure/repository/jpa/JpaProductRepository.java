package com.bicky.demopayment.productservice.product.infrastructure.repository.jpa;

import com.bicky.demopayment.productservice.product.infrastructure.repository.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JpaProductRepository extends JpaRepository<ProductModel, Long>, PagingAndSortingRepository<ProductModel, Long> {
}
