package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentMethodModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("payment.JpaPaymentMethodRepository")
public interface JpaPaymentMethodRepository extends JpaRepository<PaymentMethodModel, Long>  {
    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO " +
                    " payment_methods (user_id, payment_provider, payment_method_id, card_detail) " +
                    " VALUES (:userId, :paymentProvider, :paymentMethodId, CAST(:cardDetail AS jsonb))",
            nativeQuery = true
    )
    void savePaymentMethod(Long userId, String paymentProvider, String paymentMethodId, String cardDetail);

    @Transactional
    @Query(
            value = " SELECT * from " +
                    " payment_methods where " +
                    " user_id = :userId and payment_provider = :paymentProvider " +
                    " AND CAST(card_detail->>'cardNumber' as varchar) = CAST(:cardNumber as varchar) " +
                    " AND CAST(card_detail->>'expiryMonth' as varchar) = CAST(:expMonth as varchar) " +
                    " AND CAST(card_detail->>'expiryYear' as varchar) = CAST(:expYear as varchar) " +
                    " limit 1",
            nativeQuery = true
    )
    PaymentMethodModel findBy(Long userId, String paymentProvider, String cardNumber, String expMonth, String expYear);
}
