package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final PaymentEventProducer paymentEventProducer;

    public void sendMessage() {
        long userId = 123L;
        PaymentEvent paymentEvent = new PaymentEvent(userId, LocalDateTime.now(), 2000d);
        paymentEventProducer.sendPaymentEvent(paymentEvent);
        log.info("Sent payment event");
    }

    @CircuitBreaker(name = "NotificationServiceBreaker", fallbackMethod = "fallback")
    public String getUserId() {
        throw new NotFoundException("user not found");
    }

    public String fallback() {
        return "fallback";
    }
}
