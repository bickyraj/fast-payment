package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.payment.converter.CardDetailsConverter;
import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "payment_methods",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "payment_provider", "card_detail"}
        )
)
@Data
public class PaymentMethodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserModel user;

    @Column(name = "payment_provider", nullable = false)
    private String paymentProvider;

    @Column(name = "payment_method_id", nullable = false)
    private String paymentMethodId;

    @Column(name = "card_detail", columnDefinition = "jsonb", nullable = false)
//    @Convert(converter = CardDetailsConverter.class)
    private String cardDetail;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    public static PaymentMethodModel fromEntity(PaymentMethod paymentMethod) {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setId(paymentMethod.getId());
        paymentMethodModel.setUser(UserModel.fromEntity(paymentMethod.getUser()));
        paymentMethodModel.setPaymentProvider(paymentMethod.getPaymentProvider().name());
        try {
            paymentMethodModel.setCardDetail(objectMapper.writeValueAsString(paymentMethod.getCardDetail()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        paymentMethodModel.setPaymentMethodId(paymentMethod.getPaymentMethodId());
        return paymentMethodModel;
    }

    public static PaymentMethod toEntity(PaymentMethodModel paymentMethodModel) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return PaymentMethod.builder()
                    .id(paymentMethodModel.getId())
                    .cardDetail(objectMapper.readValue(paymentMethodModel.getCardDetail(), CardDetail.class))
                    .paymentMethodId(paymentMethodModel.getPaymentMethodId())
                    .paymentProvider(PaymentProvider.valueOf(paymentMethodModel.getPaymentProvider()))
                    .user(UserModel.toEntity(paymentMethodModel.getUser()))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
