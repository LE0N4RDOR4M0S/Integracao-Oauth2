package com.example.integracaomtloginoauth2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME_DE_USUARIO", nullable = false, length = 150)
    private String username;

    @Column(name = "EMAIL", nullable = false, length = 150)
    private String email;

    @Column(name = "NOME", nullable = false, length = 150)
    private String name;

    @Column(name = "CPF", nullable = false, length = 14)
    private String cpf;

    @Column(name = "SENHA", length = 72)
    private String password;
}
