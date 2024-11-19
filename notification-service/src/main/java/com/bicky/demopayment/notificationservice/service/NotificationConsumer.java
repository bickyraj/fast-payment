package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "report-service-group")
    public void consumePaymentSuccessMessage(PaymentEvent paymentEvent) {
        // Process the message
        emailService.sendSimpleEmail();
        System.out.println("Received payment event: " + paymentEvent);
        sendEmailNotification(paymentEvent);
    }

    public void sendEmailNotification(PaymentEvent paymentEvent) {
        // Logic to send email
        System.out.println("Sending email for payment event: " + paymentEvent);
        emailService.sendSimpleEmail();
    }
}
