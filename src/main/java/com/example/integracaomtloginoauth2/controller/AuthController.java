package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.AuthResponse;
import com.example.integracaomtloginoauth2.model.LoginRequest;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.service.JwtTokenService;
import com.example.integracaomtloginoauth2.service.UserAuthenticationService;
import jakarta.validation.Valid;
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

    public AuthController(UserAuthenticationService userAuthenticationService, JwtTokenService jwtTokenService) {
        this.userAuthenticationService = userAuthenticationService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/auth/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
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
    public ResponseEntity<?> logout(@CookieValue(value = "JSESSIONID", required = false) String token) {
        try {
            if (token != null) {
                token = "Bearer " + token;
                if (token.startsWith("Bearer ") && token.chars().filter(ch -> ch == '.').count() == 2) {
                    jwtTokenService.invalidateToken(token);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido");
                }
            } else {
                System.out.println("No token received in the cookie");
            }
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("Logout realizado com sucesso");
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Logout failed");
        }
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }

}