package com.bicky.demopayment.productservice.product.entrypoint.rest.requestbody;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProductRequestBody {
    private String name;
    private double price;
    private String description;
    private MultipartFile productImage;
}
