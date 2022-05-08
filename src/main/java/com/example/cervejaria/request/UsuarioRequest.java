package com.example.cervejaria.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    private String username;
    private String password;
    private String nome;
    private String email;
    private String telefone;
}
