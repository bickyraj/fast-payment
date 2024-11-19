package com.bicky.demopayment.notificationservice.service;

import com.bicky.demopayment.notificationservice.shared.valueobject.PaymentEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private static final String TOPIC = "payment-topic"; // Define the topic name

    public PaymentEventProducer(KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send PaymentEvent
    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        kafkaTemplate.send(TOPIC, paymentEvent.getUserId().toString(), paymentEvent);  // Sending message with userId as the key
        System.out.println("PaymentEvent sent: " + paymentEvent);
    }
}
