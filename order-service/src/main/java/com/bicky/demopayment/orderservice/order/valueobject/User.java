package com.bicky.demopayment.orderservice.order.valueobject;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String keycloakId;
    private String email;

    public static User getEmptyObject() {
        return new User(null, null, null);
    }
}