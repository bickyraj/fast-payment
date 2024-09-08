package com.bicky.demopayment.productservice.product.domain.repository.elastic;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProduct, String> {


    @Query("{\"wildcard\": {\"name\": {\"value\": \"*?0*\"}}}")
    Page<Product> findByNameContaining(String substring, Pageable pageable);
}
