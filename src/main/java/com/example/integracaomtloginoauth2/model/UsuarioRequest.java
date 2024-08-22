package com.example.integracaomtloginoauth2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private String username;
    private String email;
    private String cpf;
    private String password;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setUsername(this.username);
        usuario.setEmail(this.email);
        usuario.setCpf(this.cpf);
        usuario.setPassword(this.password);
        return usuario;
    }

    public UsuarioRequest toObject(Usuario user) {
        UsuarioRequest usuario = new UsuarioRequest(user.getUsername(),user.getEmail(),user.getCpf(),user.getPassword());
        return usuario;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UsuarioRequest)) return false;
        final UsuarioRequest other = (UsuarioRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$cpf = this.getCpf();
        final Object other$cpf = other.getCpf();
        if (this$cpf == null ? other$cpf != null : !this$cpf.equals(other$cpf)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UsuarioRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $cpf = this.getCpf();
        result = result * PRIME + ($cpf == null ? 43 : $cpf.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }
}
