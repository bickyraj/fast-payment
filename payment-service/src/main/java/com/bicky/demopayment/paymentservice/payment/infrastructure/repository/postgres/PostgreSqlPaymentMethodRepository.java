package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.postgres;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
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
    public void save(PaymentMethod paymentMethod) throws JsonProcessingException {
        String accountDetailAsJson = objectMapper.writeValueAsString(paymentMethod.getAccountDetails());
        jpaPaymentMethodRepository.savePaymentMethod(
                paymentMethod.getUser().getId(),
                paymentMethod.getPaymentType().name(),
                paymentMethod.getPaymentProvider().name(),
                accountDetailAsJson
        );
    }

    @Override
    public Set<PaymentMethod> findByUserId(Long id) {
        return Set.of();
    }
}
