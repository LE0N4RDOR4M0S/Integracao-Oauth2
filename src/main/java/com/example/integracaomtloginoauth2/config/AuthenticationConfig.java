package com.example.integracaomtloginoauth2.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class AuthenticationConfig extends AbstractHttpConfigurer<AuthenticationConfig, HttpSecurity> {

    private IdpOauth2Config idpOauth2Config;

    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
