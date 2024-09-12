package com.bicky.demopayment.orderpaymentservice.orderpayment.infrastructure.repository.model;

import com.bicky.demopayment.orderpaymentservice.orderpayment.domain.entity.OrderPayment;
import com.bicky.demopayment.orderpaymentservice.orderpayment.valueobject.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "order_payments",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"order_id", "payment_method_id"}
        )
)
public class OrderPaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodModel paymentMethod;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String stripePaymentIntentId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static OrderPaymentModel fromEntity (OrderPayment orderPayment) {
        OrderPaymentModel model = new OrderPaymentModel();
        model.setId(orderPayment.getId());
        model.setOrder(OrderModel.fromEntity(orderPayment.getOrder()));
        model.setPaymentMethod(PaymentMethodModel.fromEntity(orderPayment.getPaymentMethod()));
        model.setStatus(orderPayment.getStatus().name());
        model.setStripePaymentIntentId(orderPayment.getStripePaymentIntentId());
        return model;
    }

    public static OrderPayment toEntity (OrderPaymentModel model) {
        return OrderPayment.builder()
                .stripePaymentIntentId(model.getStripePaymentIntentId())
                .status(PaymentStatus.valueOf(model.getStatus()))
                .paymentMethod(PaymentMethodModel.toEntity(model.getPaymentMethod()))
                .order(OrderModel.toEntity(model.getOrder()))
                .build();
    }
}
