package com.bicky.demopayment.reportservice.infrastructure.services;

import com.bicky.demopayment.reportservice.domain.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
}
