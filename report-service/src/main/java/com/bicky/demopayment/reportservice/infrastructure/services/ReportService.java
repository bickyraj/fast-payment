package com.bicky.demopayment.reportservice.infrastructure.services;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import com.bicky.demopayment.reportservice.domain.entity.User;
import com.bicky.demopayment.reportservice.domain.repository.PurchaseRepository;
import com.bicky.demopayment.reportservice.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserClient userClient;
    private final PurchaseRepository purchaseRepository;

    public List<Purchase> getUserReports() {
        User user = userClient.me();
        return purchaseRepository.findAllByUserId(user.getId());
    }
}
