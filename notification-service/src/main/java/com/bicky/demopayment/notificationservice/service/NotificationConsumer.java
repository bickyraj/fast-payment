package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    @KafkaListener(topics = "payment-topic", groupId = "notification-service-group")
    public void consumePaymentSuccessMessage(PaymentEvent paymentEvent) {
        // Process the message
        System.out.println("Received payment event: " + paymentEvent);

        // Send email notification
//        sendEmailNotification(paymentEvent);
    }

    public void sendEmailNotification(PaymentEvent paymentEvent) {
        // Logic to send email
        System.out.println("Sending email for payment event: " + paymentEvent);

        // You can integrate JavaMailSender or any other email service here
        // Example: using JavaMailSender
        // sendEmail(paymentEvent);
    }
}
