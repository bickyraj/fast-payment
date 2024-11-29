package com.bicky.demopayment.paymentservice.payment.application;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.payment.infrastructure.service.PaymentProducer;
import com.bicky.demopayment.paymentservice.payment.infrastructure.service.PaymentService;
import com.bickyraj.shared_dto.PaymentEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCase {

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final Long paymentMethodId;
        private final Double amount;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final Long paymentId;
        private final Boolean success;
    }

    private final PaymentService paymentService;
    private final PaymentProducer paymentProducer;

    public Response execute(Request request) {
        Payment payment = paymentService.createPayment(request.getPaymentMethodId(), request.getAmount());
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setCreatedDate(payment.getCreatedAt());
        paymentEvent.setUserId(payment.getUser().getId());
        paymentEvent.setTotalAmount(payment.getAmount());
        paymentProducer.sendPaymentMessage(paymentEvent);
        return CreatePaymentUseCase.Response.of(payment.getId(), true);
    }
}
