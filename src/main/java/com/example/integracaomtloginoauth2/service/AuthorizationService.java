package com.example.integracaomtloginoauth2.service;

import com.example.integracaomtloginoauth2.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final UsuarioRepository usuarioRepository;

    public AuthorizationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


}
