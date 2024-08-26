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
    private String username;
    private String email;
    private String cpf;

    public UsuarioResponse toObject(Usuario user) {
        UsuarioResponse usuario = new UsuarioResponse(user.getUsername(),user.getEmail(),user.getCpf());
        return usuario;
    }

    public static List<UsuarioResponse> listOf(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setUsername(usuario.getUsername());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setCpf(usuario.getCpf());
            return usuarioResponse;
        }).collect(Collectors.toList());
    }

    public String getName() {
        return username;
    }
}
