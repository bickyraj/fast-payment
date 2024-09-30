package com.bicky.demopayment.productservice.product.entrypoint.rest;

import com.bicky.demopayment.productservice.common.pagination.PageResponse;
import com.bicky.demopayment.productservice.product.application.CreateProductUseCase;
import com.bicky.demopayment.productservice.product.application.GetAllProductsUseCase;
import com.bicky.demopayment.productservice.product.application.GetElasticProductsUseCase;
import com.bicky.demopayment.productservice.product.application.SearchProductUseCase;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.entrypoint.rest.requestbody.CreateProductRequestBody;
import com.bicky.demopayment.productservice.product.infrastructure.services.MinIOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUserCase;
    private final GetElasticProductsUseCase getElasticProductsUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final SearchProductUseCase searchProductUseCase;
    private final MinIOService minIOService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public Boolean create(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("productImage") MultipartFile productImage
    ) {
        CreateProductRequestBody productRequestBody = new CreateProductRequestBody();
        productRequestBody.setName(name);
        productRequestBody.setPrice(price);
        productRequestBody.setDescription(description);
        productRequestBody.setProductImage(productImage);
        return createProductUserCase.execute(CreateProductUseCase.Request.of(
                        productRequestBody.getName(),
                        productRequestBody.getPrice(),
                        productRequestBody.getDescription(),
                        productRequestBody.getProductImage()
            ))
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
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        Set<String> namesSet = new HashSet<>();
        namesSet.addAll(Arrays.asList(names1));
        namesSet.addAll(Arrays.asList(names2));
        namesSet.toArray(new String[0]);
        return searchProductUseCase.execute(SearchProductUseCase.Request.of(page, size, name)).getProducts();
    }

    @GetMapping("/{bucketName}/{fileName}")
    public ResponseEntity<InputStream> getImage(@PathVariable String bucketName, @PathVariable String fileName) {
        try {
            InputStream imageStream = minIOService.getObject(bucketName, fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/webp");
            return new ResponseEntity<>(imageStream, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
