package com.bicky.demopayment.userservice.user.entrypoint.rest;

import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class RegisterUserRequestBody {
    public String email;
    public String password;
    public String username;
    public String firstName;
    public String lastName;

    public static UserEntity toEntity(RegisterUserRequestBody registerUserRequestBody) {
        return UserEntity.builder()
                .email(registerUserRequestBody.email)
                .password(registerUserRequestBody.password)
                .username(registerUserRequestBody.username)
                .firstName(registerUserRequestBody.firstName)
                .lastName(registerUserRequestBody.lastName)
                .build();
    }
}
