package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    public static final String PAYMENT_TOPIC = "payment-topic";
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentMessage(PaymentEvent paymentEvent) {
        System.out.println("after payment");
        System.out.println(paymentEvent);
        kafkaTemplate.send(PAYMENT_TOPIC, paymentEvent);
    }
}
