package com.bicky.demopayment.userservice.user.infrastructure.services;

import com.bicky.demopayment.userservice.user.domain.entity.UserEntity;
import com.bicky.demopayment.userservice.user.domain.repository.UserRepository;
import com.bicky.demopayment.userservice.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UsersResource userResource;
    private final RealmResource realmResource;
    private final PasswordEncoder passwordEncoder;

    public UserEntity getCurrentUser() {
        String keycloakId = SecurityUtils.getCurrentUserId();
        return userRepository.findByKeycloakId(keycloakId);
    }

    public void saveUser(UserEntity user) {
        UserRepresentation keycloakUser = getUserRepresentation(user);
        Response response = userResource.create(keycloakUser);

        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            String keycloakId = extractUserIdFromLocationHeader(response);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setKeycloakId(keycloakId);
            RoleRepresentation role = realmResource.roles().get("app_user").toRepresentation();
            userResource.get(keycloakId).roles().realmLevel().add(Collections.singletonList(role));
            userRepository.save(user);
        }
    }

    private static UserRepresentation getUserRepresentation(UserEntity user) {
        UserRepresentation keycloakUser = new UserRepresentation();
        keycloakUser.setUsername(user.getUsername());
        keycloakUser.setEmail(user.getEmail());
        keycloakUser.setFirstName(user.getFirstName());
        keycloakUser.setLastName(user.getLastName());
        keycloakUser.setEnabled(true);
        keycloakUser.setEmailVerified(true);
        keycloakUser.setRequiredActions(Collections.emptyList());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(user.getPassword());
        keycloakUser.setCredentials(Collections.singletonList(credentialRepresentation));
        return keycloakUser;
    }

    private String extractUserIdFromLocationHeader(Response response) {
        List<Object> locationHeader = response.getHeaders().get("Location");
        if (locationHeader != null && !locationHeader.isEmpty()) {
            String location = locationHeader.get(0).toString();
            return location.substring(location.lastIndexOf("/") + 1);
        }
        return null;
    }
}
