package com.example.integracaomtloginoauth2.controller;

import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.model.UsuarioResponse;
import com.example.integracaomtloginoauth2.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @PutMapping("/{id}")
    public UsuarioResponse updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequest user) {
        return usuarioService.update(id, user);
    }

    @PostMapping("/register")
    public RedirectView saveUsuario(@RequestParam MultiValueMap<String, String> paramMap) {
        UsuarioRequest user = new UsuarioRequest();
        user.setUsername(paramMap.getFirst("username"));
        user.setEmail(paramMap.getFirst("email"));
        user.setCpf(paramMap.getFirst("cpf"));
        user.setPassword(paramMap.getFirst("password"));
        usuarioService.save(user);
        return new RedirectView("/auth/login/"+user.getUsername());
    }
}
