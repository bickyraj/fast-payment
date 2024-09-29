package com.bicky.demopayment.reportservice.infrastructure.repository.nosql;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import com.bicky.demopayment.reportservice.domain.repository.PurchaseRepository;
import com.bicky.demopayment.reportservice.infrastructure.repository.model.PurchaseModel;
import com.bicky.demopayment.reportservice.infrastructure.repository.mongo.MongoPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NoSqlPurchaseRepository implements PurchaseRepository {
    private final MongoPurchaseRepository mongoPurchaseRepository;

    @Override
    public void save(Purchase purchase) {
        mongoPurchaseRepository.save(PurchaseModel.fromEntity(purchase));
    }

    @Override
    public Optional<Purchase> findByUserId(Long userId) {
        return mongoPurchaseRepository.findByUserId(userId).map(PurchaseModel::toEntity);
    }

    @Override
    public List<Purchase> findAllByUserId(Long userId) {
        return mongoPurchaseRepository.findAllByUserId(userId).stream().map(PurchaseModel::toEntity).toList();
    }
}
