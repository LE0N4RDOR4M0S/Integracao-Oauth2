package com.example.integracaomtloginoauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UIController {

    @GetMapping("/")
    public String getIndexPage(Model model, Principal principal) {
        if (principal != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            model.addAttribute("isAuthenticated", true);
            String name = oauth2User.getAttribute("name");
            if (name == null) {
                name = oauth2User.getAttribute("preferred_username");
            }
            model.addAttribute("name", name);
            Set<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());
            model.addAttribute("isNice", roles.contains("ROLE_admin"));

        } else {
            model.addAttribute("isAuthenticated", false);
        }

        return "index";
    }

    @GetMapping("/nice")
    public String getNicePage() {
        return "nice";
    }
}