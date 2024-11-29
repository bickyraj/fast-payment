package com.bicky.demopayment.notificationservice.service;

import com.bickyraj.shared_dto.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentEventProducer {

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private static final String TOPIC = "payment-topic"; // Define the topic name

    public PaymentEventProducer(KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        // new topic
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send PaymentEvent
    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        kafkaTemplate.send(TOPIC, paymentEvent.getUserId().toString(), paymentEvent);  // Sending message with userId as the key
        log.info("Payment event sent to topic {}", TOPIC);
    }
}
