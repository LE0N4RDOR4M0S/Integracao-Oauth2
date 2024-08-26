package com.example.integracaomtloginoauth2.service;

import com.example.integracaomtloginoauth2.model.Usuario;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final HttpSession session;

    public UserAuthenticationService(UsuarioRepository usuarioRepository, HttpSession session) {
        this.usuarioRepository = usuarioRepository;
        this.session = session;
    }

    public Authentication authenticateUser(@Valid UsuarioRequest usuario) {
        session.setAttribute("usuario", usuario);
        return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
    }

    public Optional<Usuario> getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Usuario usuario) {
           return Optional.of(usuario);
        }
        if (authentication.getPrincipal() instanceof OAuth2User auth2User) {
            String email = auth2User.getAttribute("email");
            return usuarioRepository.findByEmail(email);
        }
        return Optional.empty();
    }

    public boolean isAuthenticated() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && (authentication.getPrincipal() instanceof Usuario
                || authentication.getPrincipal() instanceof OAuth2User);
    }
}
