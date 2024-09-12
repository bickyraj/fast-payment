package com.bicky.demopayment.paymentservice.payment.application;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.service.PaymentServiceProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentCustomerId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
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
        private final String paymentIntentId;
        private final Boolean success;
    }

    private final PaymentMethodRepository paymentMethodRepository;
    private final UserPaymentProviderRepository userPaymentProviderRepository;

    public Response execute(Request request) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId());
        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(paymentMethod.getPaymentProvider());
        UserPaymentProvider userPaymentProvider = userPaymentProviderRepository.getUserPaymentProviderBy(
          paymentMethod.getUser().getId(),
          paymentMethod.getPaymentProvider()
        );
        PaymentIntentID paymentIntentID = paymentServiceProvider.createPayment(
                PaymentCustomerId.of(userPaymentProvider.getProviderCustomerId()),
                PaymentMethodId.of(paymentMethod.getPaymentMethodId()),
                request.getAmount()
        );
        return CreatePaymentUseCase.Response.of(paymentIntentID.getValue(), true);
    }
}
