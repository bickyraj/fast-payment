package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "user_payment_provider",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "payment_provider"}
        )
)
public class UserPaymentProviderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(nullable = false)
    private String paymentProvider;

    @Column(name = "provider_customer_id", nullable = false)
    private String providerCustomerId;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP")
    private LocalDate createdAt;

    public static UserPaymentProviderModel fromEntity(UserPaymentProvider userPaymentProvider) {
        UserPaymentProviderModel userPaymentProviderModel = new UserPaymentProviderModel();
        userPaymentProviderModel.setUser(UserModel.fromEntity(userPaymentProvider.getUser()));
        userPaymentProviderModel.setProviderCustomerId(userPaymentProvider.getProviderCustomerId());
        userPaymentProviderModel.setPaymentProvider(userPaymentProvider.getPaymentProvider().name());
        return userPaymentProviderModel;
    }

    public static UserPaymentProvider toEntity(UserPaymentProviderModel userPaymentProviderModel) {
        return UserPaymentProvider.builder()
                .user(UserModel.toEntity(userPaymentProviderModel.getUser()))
                .paymentProvider(PaymentProvider.valueOf(userPaymentProviderModel.getPaymentProvider()))
                .providerCustomerId(userPaymentProviderModel.getProviderCustomerId()).build();
    }
}
