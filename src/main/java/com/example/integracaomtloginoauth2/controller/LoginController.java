package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.service.UserAuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private static final String error = "error";

    private final UserAuthenticationService userAuthenticationService;

    public LoginController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        if (userAuthenticationService.isAuthenticated()) {
            return "redirect:/index.html";
        }
        return "login.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
