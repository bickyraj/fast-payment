package com.bicky.demopayment.productservice.product.domain.repository.elastic;

import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProduct, String> {
}
