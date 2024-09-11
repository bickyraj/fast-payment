package com.bicky.demopayment.paymentservice.payment.domain.entity;

import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class UserPaymentProvider {
    private Long id;
    private User user;
    private String providerCustomerId;
    private PaymentProvider paymentProvider;

    public static UserPaymentProvider emptyObject() {
        return new UserPaymentProvider(null, null, null, null);
    }

    public static boolean isEmptyObject(UserPaymentProvider userPaymentProvider) {
        return userPaymentProvider.getUser() == null;
    }
}
