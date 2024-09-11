package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa;

import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.UserPaymentProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaUserPaymentProviderRepository extends JpaRepository<UserPaymentProviderModel, Long> {
    Optional<UserPaymentProviderModel> findByUserIdAndPaymentProvider(Long userId, String paymentProvider);
}
