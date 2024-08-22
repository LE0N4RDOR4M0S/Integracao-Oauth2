package com.example.integracaomtloginoauth2.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AplicationConfig {

    private final AplicationProperties aplicationProperties;

    public AplicationConfig(AplicationProperties aplicationProperties) {
        this.aplicationProperties = aplicationProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(aplicationProperties.getApiUrl()))
                .info(
                        new Info()
                                .title(aplicationProperties.getName())
                                .version(aplicationProperties.getEnv() + "-" + aplicationProperties.getVersion())
                                .description(aplicationProperties.getDescription()));
    }

    @Bean
    public GroupedOpenApi appOpenApi() {
        return GroupedOpenApi.builder().group("Servidor de Autorização").pathsToMatch("/**").build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(20000))
                .setReadTimeout(Duration.ofMillis(20000))
                .build();
    }
}
