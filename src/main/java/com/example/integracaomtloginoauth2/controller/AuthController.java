package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.AuthResponse;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.service.JwtTokenService;
import com.example.integracaomtloginoauth2.service.UserAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class AuthController {
    private final UserAuthenticationService userAuthenticationService;
    private final JwtTokenService jwtTokenService;

    public AuthController(UserAuthenticationService userAuthenticationService, JwtTokenService jwtTokenService) {
        this.userAuthenticationService = userAuthenticationService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UsuarioRequest loginRequest) {

        Authentication authentication = userAuthenticationService.authenticateUser(loginRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenService.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

}
