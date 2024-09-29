package com.bicky.demopayment.reportservice.domain.repository;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    void save(Purchase purchase);
    Optional<Purchase> findByUserId(Long userId);
    List<Purchase> findAllByUserId(Long userId);
}
