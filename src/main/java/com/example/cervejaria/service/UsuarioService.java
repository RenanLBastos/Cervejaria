package com.example.cervejaria.service;

import com.example.cervejaria.dto.Usuario;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<ApiError> createNewusuario(Usuario usuario) throws Exception {
        Iterable<Usuario> allUsuarios = getAllUsuarios();
        for (Usuario usuarioObj : allUsuarios) {
            if (usuario.getEmail().toLowerCase(Locale.ROOT).trim().equals(usuarioObj.getEmail().toLowerCase(Locale.ROOT).trim())) {
                return new ResponseEntity<>(new ApiError(200, "Usuário " + usuario.getNome() + " já cadastrado", "create ok"), HttpStatus.OK);
            }
        }
        try {
            this.usuarioRepository.save(usuario);
            return new ResponseEntity<>(new ApiError(200, "Usuário " + usuario.getNome() + " cadastrado com sucesso", "create ok"), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nome do usuário obrigatório", e);
        }
    }

    public Iterable<Usuario> getAllUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        Optional<Usuario> usuarioRepositoryById = this.usuarioRepository.findById(id);

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

    public Optional<Usuario> getUsuarioByEmail(String email) {
        try {
            Iterable<Usuario> allUsuarios = getAllUsuarios();
            for (Usuario usuarioObj : allUsuarios) {
                if (email.toLowerCase(Locale.ROOT).trim().equals(usuarioObj.getEmail().toLowerCase(Locale.ROOT).trim())) {
                    return getUsuarioById(usuarioObj.getId());
                }
            }
            throw new ResourceNotFoundException();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Usuário Not Found", e);
        }
    }

    public ResponseEntity<ApiError> updateUsuario(Integer id, @RequestBody Usuario p) {

        Optional<Usuario> usuarioToUpdateOptional = this.usuarioRepository.findById(id);
        try {
            if (usuarioToUpdateOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                Usuario usuarioToUpdate = usuarioToUpdateOptional.get();

                if (p.getNome() != null) {
                    usuarioToUpdate.setNome(p.getNome());
                }

                if (p.getUserName() != null) {
                    usuarioToUpdate.setUserName(p.getUserName());
                }
                
                if (p.getPassword() != null) {
                    usuarioToUpdate.setPassword(p.getPassword());
                }
                
                if (p.getEmail() != null) {
                    usuarioToUpdate.setEmail(p.getEmail());
                }
                
                if (p.getTelefone() != null) {
                    usuarioToUpdate.setTelefone(p.getTelefone());
                }

                this.usuarioRepository.save(usuarioToUpdate);

                //TODO criar dto usuario, service e repository
                return new ResponseEntity<>(new ApiError(200, "Usuário " + usuarioToUpdate.getNome() + " Atualizado", "put ok"), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Usuário Not Found", e);
        }
    }

    public ResponseEntity<ApiError> deleteusuario(Integer id) {
        Optional<Usuario> usuarioToDeleteOptional = this.usuarioRepository.findById(id);
        try {
            if (usuarioToDeleteOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                Usuario usuario = usuarioToDeleteOptional.get();
                this.usuarioRepository.delete(usuario);
                return new ResponseEntity<>(new ApiError(200, "Usuário " + usuario.getNome() + " deletado com sucesso", "delete ok"), HttpStatus.OK);

            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "usuario Not Found", e);
        }
    }
}
