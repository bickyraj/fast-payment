package com.bicky.demopayment.reportservice.infrastructure.repository.mongo;

import com.bicky.demopayment.reportservice.infrastructure.repository.model.PurchaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoPurchaseRepository extends MongoRepository<PurchaseModel, String> {
    Optional<PurchaseModel> findByUserId(Long userId);
    List<PurchaseModel> findAllByUserId(Long userId);
}
