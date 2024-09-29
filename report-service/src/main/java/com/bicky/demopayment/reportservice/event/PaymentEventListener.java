package com.bicky.demopayment.reportservice.event;

import com.bicky.demopayment.sharedmodule.dto.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventListener {
    @KafkaListener(topics = "payment-topic", groupId = "report-service-group")
    public void listen(PaymentEvent paymentEvent) {
        System.out.println("Received Payment Event: " + paymentEvent);
    }
}
