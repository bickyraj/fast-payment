package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import com.bicky.demopayment.productservice.product.infrastructure.services.ProductService;
import lombok.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CreateProductUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private String name;
        private double price;
        private String description;
        private MultipartFile productImage;

        public boolean isValid() {
            return name != null && price != 0;
        }
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final ProductService productService;

    public Response execute(Request request) {
        if (!request.isValid()) {
            return Response.of(false);
        }

        productService.saveProduct(request.name, request.price, request.description, request.productImage);
        return Response.of(true);
    }
}
