package com.bicky.demopayment.paymentservice.payment.application;

import com.bicky.demopayment.paymentservice.payment.infrastructure.service.UserPaymentService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.PaymentMethodRequestBody;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;

@Component
@RequiredArgsConstructor
@Slf4j
public class SavePaymentMethodUseCase {

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final PaymentMethodRequestBody requestBody;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Boolean success;
    }

    private final UserPaymentService userPaymentService;

    public Response execute(Request request) {
        AccountDetails accountDetails = AccountDetails.builder()
                .cardHolderName(request.getRequestBody().getCardHolderName())
                .cardNumber(request.getRequestBody().getCardNumber())
                .expiryMonth(request.getRequestBody().getExpiryMonth())
                .expiryYear(request.getRequestBody().getExpiryYear())
                .build();

        return Response.of(userPaymentService.savePaymentMethod(accountDetails, request.getRequestBody().getProvider()));
    }
}
