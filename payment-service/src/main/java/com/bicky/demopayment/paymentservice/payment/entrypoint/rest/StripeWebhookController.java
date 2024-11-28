package com.bicky.demopayment.paymentservice.payment.entrypoint.rest;

import com.bicky.demopayment.paymentservice.payment.application.UpdatePaymentStatusUseCase;
import com.bicky.demopayment.paymentservice.stripe.webhook.StripePaymentIntentWebhookPayload;
import com.bicky.demopayment.paymentservice.stripe.webhook.StripePaymentIntentWebhookPayloadConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/stripe/webhook")
public class StripeWebhookController {

    private final UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

    @PostMapping
    public void handleStripeWebhook(@RequestBody String payload,
                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        updatePaymentStatusUseCase
                .execute(UpdatePaymentStatusUseCase.Request.of(
                        payload,
                        sigHeader
                ));
    }
}
