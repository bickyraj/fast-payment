package com.bicky.demopayment.productservice.product.domain.entity.elastic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "products")
public class ElasticProduct {
    @Id
    private String id;
    private String name;
    private String description;
    private String price;
}
