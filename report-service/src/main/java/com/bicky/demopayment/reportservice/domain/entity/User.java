package com.bicky.demopayment.reportservice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class User {
    private Long id;
    private String email;
}
