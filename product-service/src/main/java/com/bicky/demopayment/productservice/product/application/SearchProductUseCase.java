package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.common.pagination.PageResponse;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProductUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final PageResponse<Product> products;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final int page;
        private final int size;
        private final String name;
    }

    private final ElasticProductRepository elasticProductRepository;

    public SearchProductUseCase.Response execute(SearchProductUseCase.Request request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Product> products = elasticProductRepository.findByNameContaining(request.getName(), pageable);
        return SearchProductUseCase.Response.of(new PageResponse<>(products));
    }
}
