package com.example.integracaomtloginoauth2.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class RoleService {
    private final Keycloak keycloakAdminClient;
    private final String REALM_NAME = "baeldung";

    public RoleService(Keycloak keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    public void createRealmRole(String roleName) {
        RoleRepresentation newRole = new RoleRepresentation();
        newRole.setName(roleName);
        newRole.setClientRole(false);
        keycloakAdminClient.realm(REALM_NAME).roles().create(newRole);
    }
}
