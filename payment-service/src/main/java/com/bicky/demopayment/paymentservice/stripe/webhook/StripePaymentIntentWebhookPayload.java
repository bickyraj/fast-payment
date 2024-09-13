package com.bicky.demopayment.paymentservice.stripe.webhook;

import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StripePaymentIntentWebhookPayload {
    private String eventId;
    private String paymentIntentId;
    private PaymentStatus status;
}
