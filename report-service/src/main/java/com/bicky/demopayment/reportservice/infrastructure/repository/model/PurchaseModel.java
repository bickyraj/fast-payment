package com.bicky.demopayment.reportservice.infrastructure.repository.model;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchases")
public class PurchaseModel {
    @Id
    private String id;
    private Long userId;
    private double amount;
    private LocalDateTime createdAt;

    public static PurchaseModel fromEntity(Purchase purchase) {
        PurchaseModel p = new PurchaseModel();
        p.setId(purchase.getId());
        p.setAmount(purchase.getAmount());
        p.setCreatedAt(purchase.getCreatedAt());
        p.setUserId(purchase.getUserId());
        return p;
    }

    public static Purchase toEntity(PurchaseModel p) {
        Purchase purchase = new Purchase();
        purchase.setId(p.getId());
        purchase.setAmount(p.getAmount());
        purchase.setCreatedAt(p.getCreatedAt());
        purchase.setUserId(p.getUserId());
        return purchase;
    }
}
