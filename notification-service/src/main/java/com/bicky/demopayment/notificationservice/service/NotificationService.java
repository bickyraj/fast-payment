package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final PaymentEventProducer paymentEventProducer;

    public void sendMessage() {
        long userId = 123L;
        PaymentEvent paymentEvent = new PaymentEvent(userId, LocalDateTime.now(), 2000d);
        paymentEventProducer.sendPaymentEvent(paymentEvent);
        System.out.println("Sent payment event");
    }
}
