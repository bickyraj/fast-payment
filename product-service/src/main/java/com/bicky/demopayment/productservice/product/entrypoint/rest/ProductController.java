package com.bicky.demopayment.productservice.product.entrypoint.rest;

import com.bicky.demopayment.productservice.common.pagination.PageResponse;
import com.bicky.demopayment.productservice.product.application.*;
import com.bicky.demopayment.productservice.product.domain.entity.Product;
import com.bicky.demopayment.productservice.product.entrypoint.rest.requestbody.CreateProductRequestBody;
import com.bicky.demopayment.productservice.product.infrastructure.services.MinIOService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Filter;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final CreateProductUseCase createProductUserCase;
    private final GetElasticProductsUseCase getElasticProductsUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final SearchProductUseCase searchProductUseCase;
    private final MinIOService minIOService;
    private final FilterProductUseCase filterProductUseCase;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public Boolean create(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("productImage") MultipartFile productImage
    ) {
        log.info("Create product: {}", name);
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
    @Observed(
            name = "user.name",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
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

    @GetMapping("/images/{bucketName}/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String bucketName, @PathVariable String fileName) {
        try {
            InputStream imageStream = minIOService.getObject(bucketName, fileName);
            InputStreamResource resource = new InputStreamResource(imageStream);
            String extension = StringUtils.getFilenameExtension(fileName);
            MediaType mediaType = MediaType.IMAGE_JPEG;
            if (extension != null) {
                switch (extension.toLowerCase()) {
                    case "png":
                        mediaType = MediaType.IMAGE_PNG;
                        break;
                    case "gif":
                        mediaType = MediaType.IMAGE_GIF;
                        break;
                    case "webp":
                        mediaType = MediaType.parseMediaType("image/webp");
                        break;
                    case "avif":
                        mediaType = MediaType.parseMediaType("image/avif");
                        break;
                    // Add more cases if needed
                }
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Product>> filterProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) String sortBy
    ) {
        FilterProductUseCase.Response response = filterProductUseCase.execute(
                FilterProductUseCase.Request.of(
                        name,
                        page,
                        pageSize,
                        sortBy
                ));
        return new ResponseEntity<>(response.getProducts(), HttpStatus.OK);
    }
}
