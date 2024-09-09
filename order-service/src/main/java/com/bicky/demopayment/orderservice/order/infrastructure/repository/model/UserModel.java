package com.bicky.demopayment.orderservice.order.infrastructure.repository.model;

import com.bicky.demopayment.orderservice.order.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    private Long id;

    @Column(unique = true, name = "keycloak_id")
    private String keycloakId;

    public static User toEntity(UserModel userModel) {
        return User.builder().id(userModel.getId()).keycloakId(userModel.getKeycloakId()).build();
    }

    public static UserModel fromEntity(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setKeycloakId(user.getKeycloakId());
        return userModel;
    }
}
