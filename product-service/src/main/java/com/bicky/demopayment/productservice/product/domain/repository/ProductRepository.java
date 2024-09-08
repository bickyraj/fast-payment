package com.bicky.demopayment.productservice.product.domain.repository;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll(Pageable pageable);
}
