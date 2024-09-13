package com.bicky.demopayment.paymentservice.stripe.webhook;

import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StripePaymentIntentWebhookPayloadConverter implements Converter<String, StripePaymentIntentWebhookPayload> {
    private final ObjectMapper objectMapper;

    @Override
    public StripePaymentIntentWebhookPayload convert(@NonNull String source) {
        try {
            JsonNode node = objectMapper.readTree(source);
            StripePaymentIntentWebhookPayload payload = new StripePaymentIntentWebhookPayload();
            payload.setEventId(node.path("id").asText());
            JsonNode dataNode = node.path("data");
            if (dataNode.isMissingNode()) {
                return payload;
            }

            JsonNode objectNode = dataNode.path("object");
            if (objectNode.isMissingNode()) {
                return payload;
            }
            payload.setPaymentIntentId(objectNode.path("id").asText());


            switch (node.path("type").asText()) {
                case "payment_intent.succeeded":
                    payload.setStatus(PaymentStatus.SUCCEEDED);
                    break;
                case "payment_intent.canceled":
                    payload.setStatus(PaymentStatus.CANCELED);
                    break;
                case "payment_intent.payment_failed":
                    payload.setStatus(PaymentStatus.FAILED);
                    break;
                case "payment_intent.created":
                    payload.setStatus(PaymentStatus.CREATED);
                    break;
                default:
                    payload.setStatus(PaymentStatus.NA);
                    break;

            }
            return payload;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
