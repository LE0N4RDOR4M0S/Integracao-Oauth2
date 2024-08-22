package com.example.integracaomtloginoauth2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String username;
    private String email;
    private String cpf;
    private String password;

    public UsuarioResponse toObject(Usuario user) {
        UsuarioResponse usuario = new UsuarioResponse(user.getId(),user.getUsername(),user.getEmail(),user.getCpf(),user.getPassword());
        return usuario;
    }

    public static List<UsuarioResponse> listOf(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setUsername(usuario.getUsername());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setCpf(usuario.getCpf());
            usuarioResponse.setPassword(usuario.getPassword());
            return usuarioResponse;
        }).collect(Collectors.toList());
    }
}
