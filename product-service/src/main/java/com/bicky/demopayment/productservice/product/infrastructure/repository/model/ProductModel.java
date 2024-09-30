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
    @Column
    private String originalImageUrl;
    @Column
    private String thumbImageUrl;
    @Column
    private String mediumImageUrl;
    @Column
    private String largeImageUrl;

    public static ProductModel fromEntity(Product product) {
        ProductModel productModel = new ProductModel();
        return productModel
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setDescription(product.getDescription())
                .setLargeImageUrl(product.getLargeImageUrl())
                .setMediumImageUrl(product.getMediumImageUrl())
                .setOriginalImageUrl(product.getOriginalImageUrl())
                .setThumbImageUrl(product.getThumbImageUrl());
    }

    public static Product toEntity(ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setOriginalImageUrl(productModel.getOriginalImageUrl());
        product.setThumbImageUrl(productModel.getThumbImageUrl());
        product.setMediumImageUrl(productModel.getMediumImageUrl());
        product.setLargeImageUrl(productModel.getLargeImageUrl());
        product.setDescription(productModel.getDescription());
        return product;
    }
}
