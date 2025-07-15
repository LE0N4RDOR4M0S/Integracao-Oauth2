package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.AuthResponse;
import com.example.integracaomtloginoauth2.model.LoginRequest;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.service.JwtTokenService;
import com.example.integracaomtloginoauth2.service.TokenBlacklistService;
import com.example.integracaomtloginoauth2.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    private final UserAuthenticationService userAuthenticationService;
    private final JwtTokenService jwtTokenService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(UserAuthenticationService userAuthenticationService, JwtTokenService jwtTokenService, TokenBlacklistService tokenBlacklistService) {
        this.userAuthenticationService = userAuthenticationService;
        this.jwtTokenService = jwtTokenService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = userAuthenticationService.authenticateUser(loginRequest);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenService.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                tokenBlacklistService.blacklistToken(token);
            }

            SecurityContextHolder.clearContext();
            return ResponseEntity.ok().body("{\"message\": \"Logout realizado com sucesso\"}");
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"token.not.logout\"}");
        }
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }


    @GetMapping("/oauth2/success")
    public String oauth2Success() {
        return "index.html";
    }

}