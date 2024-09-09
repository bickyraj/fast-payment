package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import lombok.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final Product requestBody;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final ProductRepository productRepository;
    private final ElasticProductRepository elasticProductRepository;


    public Response execute(Request request) {
        Product product = productRepository.save(request.requestBody);
        ElasticProduct elasticProduct = new ElasticProduct();
        elasticProduct.setId(product.getId().toString());
        elasticProduct.setName(product.getName());
        elasticProduct.setDescription(product.getDescription());
        elasticProduct.setPrice(String.valueOf(product.getPrice()));
        elasticProductRepository.save(elasticProduct);
        return Response.of(true);
    }
}
