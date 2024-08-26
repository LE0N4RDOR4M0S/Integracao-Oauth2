package com.example.integracaomtloginoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class IntegracaoMtLoginOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(IntegracaoMtLoginOAuth2Application.class, args);
    }

}
