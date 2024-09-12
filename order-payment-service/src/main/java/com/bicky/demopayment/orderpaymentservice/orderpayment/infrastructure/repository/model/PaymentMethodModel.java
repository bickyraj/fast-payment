package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "payment_methods")
public class PaymentMethodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static PaymentMethodModel fromEntity(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setId(paymentMethod.getId());
        return paymentMethodModel;
    }

    public static PaymentMethod toEntity(PaymentMethodModel paymentMethodModel) {
        return PaymentMethod.builder()
                .id(paymentMethodModel.getId())
                .build();
    }
}
