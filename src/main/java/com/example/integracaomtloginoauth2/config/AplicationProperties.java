package com.example.integracaomtloginoauth2.config;

import jakarta.security.auth.message.config.AuthConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class AplicationProperties {
    private String env;

    private String name;

    private String version;

    private String description;

    private String apiUrl;

    @NestedConfigurationProperty
    private AuthConfig auth;

    private int passwordTokenExpiration;

    private String passwordTokenUrl;

    private String nomePerfilAdministrador;

    public boolean isDev(){
        return "dev".equalsIgnoreCase(env);
    }

    /**
     * @param token
     * @return
     */
    public String generatePasswordTokenUrl(String token) {
        return resolveHost(passwordTokenUrl + token);
    }

    /**
     * @param url
     * @return
     */
    private String resolveHost(String url) {
        return url;
    }
}
