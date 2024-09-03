package com.bicky.demopayment.userservice.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@Accessors(chain = true)
public class UserEntity {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String keycloakId;
}
