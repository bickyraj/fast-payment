package com.bicky.demopayment.productservice.product.entrypoint.rest;

import com.bicky.demopayment.productservice.common.pagination.PageResponse;
import com.bicky.demopayment.productservice.product.application.CreateProductUseCase;
import com.bicky.demopayment.productservice.product.application.GetAllProductsUseCase;
import com.bicky.demopayment.productservice.product.application.GetElasticProductsUseCase;
import com.bicky.demopayment.productservice.product.application.SearchProductUseCase;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUserCase;
    private final GetElasticProductsUseCase getElasticProductsUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final SearchProductUseCase searchProductUseCase;

    @PostMapping("/create")
    public Boolean create(@RequestBody Product product) {
        return createProductUserCase.execute(CreateProductUseCase.Request.of(product))
                .getSuccess();
    }

    @GetMapping("/elastic")
    public List<Product> getAllElasticProducts() {
        return getElasticProductsUseCase.execute().getElasticProducts();
    }

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
            ) {
        return getAllProductsUseCase.execute(GetAllProductsUseCase.Request.of(page, size)).getProducts();
    }

    @GetMapping("/search")
    public PageResponse<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String name
    ) {
        return searchProductUseCase.execute(SearchProductUseCase.Request.of(page, size, name)).getProducts();
    }
}
