package com.example.cervejaria.service;

import com.example.cervejaria.component.MontaUsuarioComponent;
import com.example.cervejaria.dto.UsuarioDto;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.UsuarioRepository;
import com.example.cervejaria.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MontaUsuarioComponent montaUsuarioComponent;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDto usuarioDto = usuarioRepository.findByUsername(username);
        if (usuarioDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(usuarioDto.getUsername(), usuarioDto.getPassword(),
                new ArrayList<>());
    }

    public ResponseEntity<ApiError> createNewusuario(UsuarioRequest usuarioRequest) {
        Iterable<UsuarioDto> allUsuarios = getAllUsuarios();
        for (UsuarioDto usuarioDtoObj : allUsuarios) {
            if (usuarioRequest.getEmail().toLowerCase(Locale.ROOT).trim().equals(usuarioDtoObj.getEmail().toLowerCase(Locale.ROOT).trim())) {
                return new ResponseEntity<>(new ApiError(200, "${USUARIO} " + usuarioRequest.getNome() + " já cadastrado", "create ok"), HttpStatus.OK);
            }
        }
        try {
            this.usuarioRepository.save(this.montaUsuarioComponent.montaUsuarioComponent(usuarioRequest));
            return new ResponseEntity<>(new ApiError(200, "${USUARIO} " + usuarioRequest.getNome() + " cadastrado com sucesso", "create ok"), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nome do usuário obrigatório", e);
        }
    }

    public Iterable<UsuarioDto> getAllUsuarios() {

        return this.usuarioRepository.findAll();
    }

    public Optional<UsuarioDto> getUsuarioById(Integer id) {
        Optional<UsuarioDto> usuarioRepositoryById = this.usuarioRepository.findById(id);

        try {
            if (usuarioRepositoryById.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                return usuarioRepositoryById;
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Usuário Not Found", e);
        }
    }

    public Optional<UsuarioDto> getUsuarioByEmail(String email) {
        try {
            Iterable<UsuarioDto> allUsuarios = getAllUsuarios();
            for (UsuarioDto usuarioDtoObj : allUsuarios) {
                if (email.toLowerCase(Locale.ROOT).trim().equals(usuarioDtoObj.getEmail().toLowerCase(Locale.ROOT).trim())) {
                    return getUsuarioById(usuarioDtoObj.getId());
                }
            }
            throw new ResourceNotFoundException();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Usuário Not Found", e);
        }
    }

    public ResponseEntity<ApiError> updateUsuario(Integer id, @RequestBody UsuarioRequest p) {

        Optional<UsuarioDto> usuarioToUpdateOptional = this.usuarioRepository.findById(id);
        try {
            if (usuarioToUpdateOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                UsuarioDto usuarioDtoToUpdate = usuarioToUpdateOptional.get();

                if (p.getNome() != null) {
                    usuarioDtoToUpdate.setNome(p.getNome());
                }

                if (p.getUsername() != null) {
                    usuarioDtoToUpdate.setUsername(p.getUsername());
                }

                if (p.getPassword() != null) {
                    usuarioDtoToUpdate.setPassword(p.getPassword());
                }

                if (p.getEmail() != null) {
                    usuarioDtoToUpdate.setEmail(p.getEmail());
                }

                if (p.getTelefone() != null) {
                    usuarioDtoToUpdate.setTelefone(p.getTelefone());
                }

                this.usuarioRepository.save(montaUsuarioComponent.montaUsuarioComponent(p));

                return new ResponseEntity<>(new ApiError(200, "${USUARIO} " + usuarioDtoToUpdate.getNome() + " Atualizado", "put ok"), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Usuário Not Found", e);
        }
    }

    public ResponseEntity<ApiError> deleteusuario(Integer id) {
        Optional<UsuarioDto> usuarioToDeleteOptional = this.usuarioRepository.findById(id);
        try {
            if (usuarioToDeleteOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                UsuarioDto usuarioDto = usuarioToDeleteOptional.get();
                this.usuarioRepository.delete(usuarioDto);
                return new ResponseEntity<>(new ApiError(200, "${USUARIO}" + usuarioDto.getNome() + " deletado com sucesso", "delete ok"), HttpStatus.OK);

            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "usuario Not Found", e);
        }
    }
}
