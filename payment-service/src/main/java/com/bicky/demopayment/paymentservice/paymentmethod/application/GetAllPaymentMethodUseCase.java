package com.bicky.demopayment.paymentservice.paymentmethod.application;

import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.service.UserPaymentMethodService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPaymentMethodUseCase {

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final List<PaymentMethod> paymentMethods;
    }

    private final UserPaymentMethodService userPaymentMethodService;
    public Response execute() {
        return Response.of(userPaymentMethodService.getAllPaymentMethods());
    }
}
