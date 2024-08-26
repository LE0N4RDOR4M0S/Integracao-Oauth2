package com.example.integracaomtloginoauth2.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public record UserPrincipals (
    String username,
    String password,
    String email,
    String name,
    String token
) implements UserDetails, OAuth2User {

    public static UserPrincipals idpOf(OAuth2User oauthUser, Usuario usuario) {
        return new UserPrincipals(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getName(),
                oauthUser.getAttribute("token")
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
