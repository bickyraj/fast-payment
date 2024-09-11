package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.postgres;

import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa.JpaUserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.UserPaymentProviderModel;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgreSqlUserPaymentProviderRepository implements UserPaymentProviderRepository {
    private final JpaUserPaymentProviderRepository jpaUserPaymentProviderRepository;

    @Override
    public boolean create(UserPaymentProvider userPaymentProvider) {
        jpaUserPaymentProviderRepository.save(UserPaymentProviderModel.fromEntity(userPaymentProvider));
        return true;
    }

    @Override
    public UserPaymentProvider getUserPaymentProviderBy(Long userId, PaymentProvider paymentProvider) {
        return jpaUserPaymentProviderRepository
                .findByUserIdAndPaymentProvider(userId, paymentProvider.name())
                .map(UserPaymentProviderModel::toEntity)
                .orElse(UserPaymentProvider.emptyObject());

    }
}
