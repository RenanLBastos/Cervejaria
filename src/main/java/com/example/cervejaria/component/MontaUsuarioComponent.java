package com.example.cervejaria.component;

import com.example.cervejaria.dto.UsuarioDto;
import com.example.cervejaria.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MontaUsuarioComponent {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public UsuarioDto montaUsuarioComponent (UsuarioRequest usuarioRequest) {
        UsuarioDto novoUsuarioDto = new UsuarioDto();
        novoUsuarioDto.setNome(usuarioRequest.getNome());
        novoUsuarioDto.setUsername(usuarioRequest.getUsername());
        novoUsuarioDto.setPassword(bcryptEncoder.encode(usuarioRequest.getPassword()));
        novoUsuarioDto.setTelefone(usuarioRequest.getTelefone());
        novoUsuarioDto.setEmail(usuarioRequest.getEmail());
        novoUsuarioDto.setTelefone(usuarioRequest.getTelefone());

        return novoUsuarioDto;
    }
}
