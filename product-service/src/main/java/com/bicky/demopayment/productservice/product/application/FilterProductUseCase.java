package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.common.pagination.PageResponse;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import com.bicky.demopayment.productservice.valueobject.ProductFilterPayload;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterProductUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Page<Product> products;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final String name;
        private final Integer page;
        private final Integer size;
        private final String sortBy;
    }

    private final ProductRepository productRepository;

    public Response execute(Request request) {
        ProductFilterPayload productFilterPayload = ProductFilterPayload.of(
                request.name,
                request.page,
                request.size,
                request.sortBy
        );
        return Response.of(productRepository.filter(productFilterPayload));
    }
}
