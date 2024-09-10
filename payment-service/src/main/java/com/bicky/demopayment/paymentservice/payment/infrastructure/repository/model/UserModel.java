package com.bicky.demopayment.paymentservice.payment.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
