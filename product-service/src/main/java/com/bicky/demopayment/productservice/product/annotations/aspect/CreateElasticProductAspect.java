package com.bicky.demopayment.productservice.product.annotations.aspect;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.domain.entity.elastic.ElasticProduct;
import com.bicky.demopayment.productservice.product.domain.repository.elastic.ElasticProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CreateElasticProductAspect {
    private final ElasticProductRepository elasticProductRepository;

    @AfterReturning(pointcut = "@annotation(com.bicky.demopayment.productservice.product.annotations.CreateElasticProduct)",
            returning = "product")
    public void createElasticProduct(Product product) {
        ElasticProduct elasticProduct = new ElasticProduct();
        elasticProduct.setId(product.getId().toString());
        elasticProduct.setName(product.getName());
        elasticProduct.setDescription(product.getDescription());
        elasticProduct.setPrice(String.valueOf(product.getPrice()));
        elasticProductRepository.save(elasticProduct);
    }
}
