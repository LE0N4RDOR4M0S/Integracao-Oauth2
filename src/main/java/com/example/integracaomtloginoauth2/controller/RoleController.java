package com.example.integracaomtloginoauth2.controller;


import com.example.integracaomtloginoauth2.service.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@Profile("!test")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/{roleName}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> createRole(@PathVariable String roleName) {
        try {
            roleService.createRealmRole(roleName);
            return ResponseEntity.status(HttpStatus.CREATED).body("Role '" + roleName + "' criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
