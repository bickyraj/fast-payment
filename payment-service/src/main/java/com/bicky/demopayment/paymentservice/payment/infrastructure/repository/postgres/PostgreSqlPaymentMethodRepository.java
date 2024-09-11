package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.postgres;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentMethodModel;
import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa.JpaPaymentMethodRepository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class PostgreSqlPaymentMethodRepository implements PaymentMethodRepository {
    private final JpaPaymentMethodRepository jpaPaymentMethodRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean save(PaymentMethod paymentMethod) {
        try {
            String cardDetailJson = objectMapper.writeValueAsString(paymentMethod.getCardDetail());
            jpaPaymentMethodRepository.savePaymentMethod(
                    paymentMethod.getUser().getId(),
                    paymentMethod.getPaymentProvider().name(),
                    paymentMethod.getPaymentMethodId(),
                    cardDetailJson
            );
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    @Override
    public boolean existsBy(
            Long userId, PaymentProvider paymentProvider, String cardNumber,
            String expirationMonth, String expirationYear
    ) {
        PaymentMethodModel paymentMethodModel = jpaPaymentMethodRepository
                .findBy(userId, paymentProvider.name(), cardNumber, expirationMonth, expirationYear);
        return paymentMethodModel != null;
    }

    @Override
    public Set<PaymentMethod> findByUserId(Long id) {
        return Set.of();
    }
}
