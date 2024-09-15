package com.bicky.demopayment.paymentservice.paymentmethod.infrastructure.repository.model;

import com.bicky.demopayment.paymentservice.paymentmethod.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity(name = "paymentmethod.UserModel")
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "keycloak_id")
    private String keycloakId;

    @Column
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PaymentMethodModel> paymentMethods;

    public static User toEntity(UserModel userModel) {
        return User.builder()
                .id(userModel.getId())
                .keycloakId(userModel.getKeycloakId())
                .email(userModel.getEmail())
                .build();
    }

    public static UserModel fromEntity(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setKeycloakId(user.getKeycloakId());
        userModel.setEmail(user.getEmail());
        return userModel;
    }
}
