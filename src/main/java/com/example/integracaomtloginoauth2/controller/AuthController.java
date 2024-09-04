package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.AuthResponse;
import com.example.integracaomtloginoauth2.model.LoginRequest;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.service.JwtTokenService;
import com.example.integracaomtloginoauth2.service.UserAuthenticationService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //TODO:Implementação do logout em JWT é feita invalidando o token no servidor e removendo o token do cliente (frontend)
    @GetMapping("/auth/logout")
    public String logout(@CookieValue(value = "JSESSIONID", required = false) String token, HttpServletResponse response) {
        if (token != null) {
            jwtTokenService.invalidateToken(token, response);
        } else {
            System.out.println("Token ausente");
        }
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }

}
