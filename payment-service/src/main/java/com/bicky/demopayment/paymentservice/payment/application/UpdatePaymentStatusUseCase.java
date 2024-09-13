package com.bicky.demopayment.paymentservice.payment.application;

import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentRepository;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.stripe.webhook.StripePaymentIntentWebhookPayload;
import com.bicky.demopayment.paymentservice.stripe.webhook.StripePaymentIntentWebhookPayloadConverter;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdatePaymentStatusUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final String payload;
        private final String signature;
    }

    @Value("${stripe.webhook.secret}")
    private String STRIPE_WEBHOOK_SECRET;

    private final StripePaymentIntentWebhookPayloadConverter stripePaymentIntentWebhookPayloadConverter;
    private final PaymentRepository paymentRepository;

    public void execute(Request request) {
        try {
            Event event = Webhook.constructEvent(
                    request.getPayload(), request.getSignature(), STRIPE_WEBHOOK_SECRET
            );

            StripePaymentIntentWebhookPayload stripePaymentIntentWebhookPayload = stripePaymentIntentWebhookPayloadConverter
                    .convert(request.getPayload());

            assert stripePaymentIntentWebhookPayload != null;
            paymentRepository.updatePaymentStatus(
                    PaymentIntentID.of(stripePaymentIntentWebhookPayload.getPaymentIntentId()),
                    stripePaymentIntentWebhookPayload.getStatus());
        } catch (Exception e) {
            System.out.println("Webhook error: " + e.getMessage());
        }
    }
}
