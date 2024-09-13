package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.Payment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.valueobject.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "payments")
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double amount;

    @Column
    private String status;

    public static PaymentModel fromEntity(Payment payment) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(payment.getId());
        paymentModel.setAmount(payment.getAmount());
        paymentModel.setStatus(payment.getStatus().name());
        return paymentModel;
    }

    public static Payment toEntity(PaymentModel paymentModel) {
        return Payment.builder()
                .amount(paymentModel.getAmount())
                .status(PaymentStatus.valueOf(paymentModel.getStatus()))
                .id(paymentModel.getId())
                .build();
    }
}
