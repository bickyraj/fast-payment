package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentMethodModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentMethodRepository extends JpaRepository<PaymentMethodModel, Long>  {
    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO payment_methods (user_id, payment_method_type, payment_provider, account_details) VALUES (:userId, :payment_method_type, :payment_provider, CAST(:accountDetails AS jsonb))",
            nativeQuery = true
    )
    void savePaymentMethod(Long userId, String payment_method_type, String payment_provider, String accountDetails);
}
