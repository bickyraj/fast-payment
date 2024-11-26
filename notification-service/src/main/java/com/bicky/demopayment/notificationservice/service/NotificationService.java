package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "NotificationServiceBreaker", fallbackMethod = "fallback")
    public String getUserId() throws Exception {
        throw new Exception("user not found");
    }

    public String fallback() {
        return "fallback";
    }
}
