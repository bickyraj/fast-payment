package com.bicky.demopayment.productservice.product.infrastructure.repository.model;

import com.bicky.demopayment.productservice.product.domain.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String description;

    public static ProductModel fromEntity(Product product) {
        ProductModel productModel = new ProductModel();
        return productModel
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setDescription(product.getDescription());
    }
}
