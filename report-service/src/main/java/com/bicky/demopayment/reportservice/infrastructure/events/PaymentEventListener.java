package com.bicky.demopayment.reportservice.infrastructure.events;

import com.bicky.demopayment.reportservice.domain.entity.Purchase;
import com.bicky.demopayment.reportservice.domain.repository.PurchaseRepository;
import com.bickyraj.shared_dto.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentEventListener {
    private final PurchaseRepository purchaseRepository;

    @KafkaListener(topics = "payment-topic", groupId = "report-service-group")
    public void listen(PaymentEvent paymentEvent) {
        Purchase purchase = new Purchase();
        purchase.setAmount(paymentEvent.getTotalAmount());
        purchase.setUserId(paymentEvent.getUserId());
        purchase.setCreatedAt(paymentEvent.getCreatedDate());
        System.out.println("payment received: " + paymentEvent);
        purchaseRepository.save(purchase);
    }
}
