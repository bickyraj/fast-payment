package com.bicky.demopayment.productservice.product.domain.repository;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.valueobject.ProductFilterPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll(Pageable pageable);
    Page<Product> filter(ProductFilterPayload filterPayload);
}
