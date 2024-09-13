package com.bicky.demopayment.paymentservice.payment.application;

import com.bicky.demopayment.paymentservice.payment.infrastructure.service.UserPaymentService;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.bicky.demopayment.paymentservice.payment.entrypoint.rest.requestbody.AddPaymentMethodRequestBody;
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
        private final PaymentMethodId paymentMethodId;
        private final PaymentProvider paymentProvider;
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
        return Response.of(userPaymentService.savePaymentMethod(request.getPaymentProvider(), request.getPaymentMethodId()));
    }
}
