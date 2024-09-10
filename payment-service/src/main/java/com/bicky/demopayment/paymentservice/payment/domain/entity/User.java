package com.bicky.demopayment.paymentservice.payment.domain.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String keycloakId;

    public static User getEmptyObject() {
        return new User(null, null);
    }
}
