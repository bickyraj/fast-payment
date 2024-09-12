package com.bicky.demopayment.userservice.user.infrastructure.reporitory.model;

import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String keycloakId;

    public static UserEntity toEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .keycloakId(userModel.getKeycloakId())
                .email(userModel.getEmail())
                .build();
    }

    public static UserModel fromEntity(UserEntity user) {
        UserModel userModel = new UserModel();
        return userModel
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setKeycloakId(user.getKeycloakId());
    }
}
