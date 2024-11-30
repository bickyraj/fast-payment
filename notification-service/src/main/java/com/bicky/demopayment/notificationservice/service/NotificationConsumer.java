package com.bicky.demopayment.notificationservice.service;

import com.bickyraj.shared_dto.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "report-service-group")
    public void consumePaymentSuccessMessage(PaymentEvent paymentEvent) {
        // Process the message
        emailService.sendSimpleEmail();
        System.out.println(Thread.currentThread().getName());
        log.info("Received payment event: " + paymentEvent);
        sendEmailNotification(paymentEvent);
    }

    public void sendEmailNotification(PaymentEvent paymentEvent) {
        // Logic to send email
        log.info("Sending payment event: " + paymentEvent);
        emailService.sendSimpleEmail();
    }
}
