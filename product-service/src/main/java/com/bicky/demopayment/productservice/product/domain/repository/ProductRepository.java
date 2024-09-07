package com.bicky.demopayment.productservice.product.domain.repository;

import com.bicky.demopayment.productservice.product.domain.entity.Product;

public interface ProductRepository {
    Product save(Product product);
}
