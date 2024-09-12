package com.bicky.demopayment.orderpaymentservice.orderpayment.application;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.User;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.client.UserClient;
import com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.service.OrderPaymentService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveOrderPaymentUseCase {
    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Request {
        private final Long orderId;
        private final Long paymentMethodId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString
    public static class Response {
        private final boolean success;
    }

    private final OrderPaymentService orderPaymentService;

    public Response execute(Request request) {
        return Response.of(orderPaymentService
                .saveOrderPayment(request.getOrderId(),
                        request.getPaymentMethodId()));
    }
}
