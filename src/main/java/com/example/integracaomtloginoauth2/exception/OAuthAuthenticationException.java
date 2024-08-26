package com.example.integracaomtloginoauth2.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class OAuthAuthenticationException extends InternalAuthenticationServiceException {
    public OAuthAuthenticationException(String code) {
        super(code);
    }
}