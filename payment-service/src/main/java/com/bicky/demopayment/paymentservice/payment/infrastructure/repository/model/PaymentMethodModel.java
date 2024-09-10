package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.payment.converter.AccountDetailConverter;
import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;

@Setter
@Getter
@Entity
@Table(name = "payment_methods",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "payment_method_type", "payment_provider", "account_details"}
        )
)
public class PaymentMethodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user;

    @Column(name = "payment_method_type", nullable = false)
    private String paymentMethodType;

    @Column(name = "payment_provider", nullable = false)
    private String paymentProvider;


    @Column(name = "account_details", nullable = false, columnDefinition = "jsonb")
    @Convert(converter = AccountDetailConverter.class)
    private AccountDetails accountDetails;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    public static PaymentMethodModel fromEntity(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setUser(UserModel.fromEntity(paymentMethod.getUser()));
        paymentMethodModel.setPaymentMethodType(paymentMethod.getPaymentType().name());
        paymentMethodModel.setPaymentProvider(paymentMethod.getPaymentProvider().name());
        paymentMethodModel.setAccountDetails(paymentMethod.getAccountDetails());
        return paymentMethodModel;
    }
}
