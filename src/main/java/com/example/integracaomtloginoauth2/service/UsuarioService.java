package com.example.integracaomtloginoauth2.service;

import com.example.integracaomtloginoauth2.model.Usuario;
import com.example.integracaomtloginoauth2.model.UsuarioRequest;
import com.example.integracaomtloginoauth2.model.UsuarioResponse;
import com.example.integracaomtloginoauth2.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método que retorna todos os usuários
     * @return List<UsuarioResponse>
     */
    public List<UsuarioResponse> findAll() {
        List<Usuario> usuarios= usuarioRepository.findAll();
        List<UsuarioResponse> users = UsuarioResponse.listOf(usuarios);
        return users;
    }

    /**
     * Método que retorna um usuário pelo id
     * @param id Long
     * @return UsuarioResponse
     */
    public UsuarioResponse findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        UsuarioResponse user = new UsuarioResponse().toObject(usuario);
        return user;
    }

    /**
     * Método que salva um usuário
     * @param usuario Usuario
     * @return UsuarioResponse
     */
    public UsuarioResponse save(UsuarioRequest usuario) {
        Usuario user = usuario.toEntity();
        usuarioRepository.save(user);
        UsuarioResponse usuarioResponse = new UsuarioResponse().toObject(user);
        return usuarioResponse;
    }

    /**
     * Método que deleta um usuário pelo id
     * @param id Long
     */
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Método que atualiza um usuário
     * @param id Long
     * @param usuario UsuarioRequest
     * @return UsuarioResponse
     */
    public UsuarioResponse update(Long id, UsuarioRequest usuario) {
        Usuario user = usuarioRepository.findById(id).orElseThrow();
        user.setUsername(usuario.getUsername());
        user.setEmail(usuario.getEmail());
        user.setCpf(usuario.getCpf());
        user.setPassword(usuario.getPassword());
        Usuario userUpdated = usuarioRepository.save(user);
        UsuarioResponse usuarioResponse = new UsuarioResponse().toObject(userUpdated);
        return usuarioResponse;
    }
}
