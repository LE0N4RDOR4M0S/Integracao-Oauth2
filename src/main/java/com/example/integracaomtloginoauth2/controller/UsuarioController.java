package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.model.UsuarioResponse;
import com.example.integracaomtloginoauth2.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public UsuarioResponse getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @GetMapping("/username/{username}")
    public UsuarioResponse getUsuarioByUsername(@PathVariable String username) {
        return usuarioService.findByUsername(username);
    }

    @GetMapping
    public Collection<UsuarioResponse> getUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping("/{id}")
    public UsuarioResponse updateUsuario(@PathVariable Long id,@RequestBody UsuarioRequest user) {
        return usuarioService.update(id, user);
    }

    @PostMapping("/register")
    public UsuarioResponse saveUsuario(@Valid @RequestBody UsuarioRequest user) {
        System.out.println("UsuarioController.saveUsuario");
        return usuarioService.save(user);
    }
}
