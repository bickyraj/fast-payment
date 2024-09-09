package com.bicky.demopayment.productservice.product.annotations.aspect;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogProductCreateAspect {
    @AfterReturning(pointcut = "@annotation(com.bicky.demopayment.productservice.product.annotations.LogProductCreate)",
            returning = "product")
    public void logProductCreate(Product product) throws Throwable {
        System.out.println(product);
    }
}
