package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.postgres;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa.JpaPaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.jpa.JpaPaymentRepository;
import com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model.PaymentModel;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgreSqlPaymentRepository implements PaymentRepository {
    private final JpaPaymentRepository jpaPaymentRepository;
    private final JpaPaymentMethodRepository jpaPaymentMethodRepository;

    @Override
    public Payment create(Payment payment) {
        return PaymentModel.toEntity(jpaPaymentRepository.save(PaymentModel.fromEntity(payment)));
    }

    @Override
    public Boolean updatePaymentStatus(PaymentIntentID paymentIntentID, PaymentStatus paymentStatus, Long eventCreatedAt) {
        Optional<PaymentModel> paymentModel = jpaPaymentRepository.findByPaymentintentId(paymentIntentID.getValue());
        if (paymentModel.isEmpty()) {
            return false;
        }
        paymentModel.get().setStatus(paymentStatus.name());
        paymentModel.get().setEventCreatedAt(eventCreatedAt);
        jpaPaymentRepository.save(paymentModel.get());
        return true;
    }

    @Override
    public Optional<Payment> getPayment(PaymentIntentID paymentIntentID) {
        return jpaPaymentRepository.findByPaymentintentId(paymentIntentID.getValue()).map(PaymentModel::toEntity);
    }
}
