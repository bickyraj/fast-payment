package com.bicky.demopayment.orderservice.order.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class KeycloakId {
    private String value;
}
