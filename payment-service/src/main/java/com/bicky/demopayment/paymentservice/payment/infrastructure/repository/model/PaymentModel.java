package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.payment.domain.entity.Payment;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentIntentID;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "payments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "paymentintent_id", "provider", "payment_method_id"}
        )
)
@Data
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user;

    @Column(name = "paymentintent_id", nullable = false)
    private String paymentintentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id", nullable = false)
    private PaymentMethodModel paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp updatedAt;

    public static PaymentModel fromEntity(Payment payment) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(payment.getId());
        paymentModel.setPaymentintentId(payment.getPaymentIntentID().getValue());
        paymentModel.setPaymentMethod(PaymentMethodModel.fromEntity(payment.getPaymentMethod()));
        paymentModel.setProvider(payment.getProvider().name());
        paymentModel.setStatus(payment.getStatus().name());
        paymentModel.setUser(UserModel.fromEntity(payment.getUser()));
        paymentModel.setAmount(payment.getAmount());
        return paymentModel;
    }

    public static Payment toEntity(PaymentModel paymentModel) {
        Payment payment = new Payment();
        payment.setId(paymentModel.getId());
        payment.setPaymentIntentID(PaymentIntentID.of(paymentModel.getPaymentintentId()));
        payment.setPaymentMethod(PaymentMethodModel.toEntity(paymentModel.getPaymentMethod()));
        payment.setProvider(PaymentProvider.valueOf(paymentModel.getProvider()));
        payment.setStatus(PaymentStatus.valueOf(paymentModel.getStatus()));
        payment.setAmount(paymentModel.getAmount());
        payment.setUser(UserModel.toEntity(paymentModel.getUser()));
        return payment;
    }
}
