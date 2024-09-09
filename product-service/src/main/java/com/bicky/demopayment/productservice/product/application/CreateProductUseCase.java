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

    public Response execute(Request request) {
        productRepository.save(request.requestBody);
        return Response.of(true);
    }
}
