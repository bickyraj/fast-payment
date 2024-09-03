package com.bicky.demopayment.productservice.product.entrypoint.rest;

import com.bicky.demopayment.productservice.product.application.CreateProductUserCase;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUserCase createProductUserCase;

    @PostMapping("/create")
    public Boolean create(@RequestBody Product product) {
        return createProductUserCase.execute(CreateProductUserCase.Request.of(product))
                .getSuccess();
    }
}
