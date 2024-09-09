package com.bicky.demopayment.orderservice.order.domain.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
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
