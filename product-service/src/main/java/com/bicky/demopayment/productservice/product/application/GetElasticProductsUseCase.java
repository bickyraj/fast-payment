package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import lombok.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetElasticProductsUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final List<Product> elasticProducts;
    }

    private final ElasticProductRepository elasticProductRepository;

    public GetElasticProductsUseCase.Response execute() {
        List<Product> listProducts = new ArrayList<>();
        for (ElasticProduct elasticProduct : elasticProductRepository.findAll()) {
            Product product = new Product();
            product.setId(Long.valueOf(elasticProduct.getId()));
            product.setName(elasticProduct.getName());
            product.setDescription(elasticProduct.getDescription());
            product.setPrice(Double.parseDouble(elasticProduct.getPrice()));
            listProducts.add(product);
        }
        return Response.of(listProducts);
    }
}
