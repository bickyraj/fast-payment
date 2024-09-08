package com.bicky.demopayment.productservice.product.application;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.repository.ProductRepository;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllProductsUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final List<Product> products;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final int page;
        private final int size;
    }

    private final ProductRepository productRepository;

    public GetAllProductsUseCase.Response execute(Request request) {
        Pageable pageable = PageRequest.of(request.page, request.size);
        return GetAllProductsUseCase.Response.of(productRepository.findAll(pageable));
    }
}
