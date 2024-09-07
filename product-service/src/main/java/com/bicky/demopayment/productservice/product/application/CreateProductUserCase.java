package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import lombok.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CreateProductUserCase {
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
    private final RestHighLevelClient restHighLevelClient;

    public Response execute(Request request) throws IOException {
        Product product = productRepository.save(request.requestBody);
        IndexRequest indexRequest = new IndexRequest("products")
                .id(product.getId().toString())
                .source("name", product.getName(),
                        "description", product.getDescription(),
                        "price", product.getPrice());
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println("Elasticsearch Response: " + indexResponse);
//        if (indexResponse.getResult() == IndexResponse.Result.CREATED) {
//            return Response.of(true);
//        } else {
//            return Response.of(false);
//        }
        return Response.of(true);
    }
}
