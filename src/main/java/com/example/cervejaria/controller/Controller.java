package com.example.cervejaria.controller;


import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.dto.Usuario;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.service.ReceitaService;
import com.example.cervejaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;



@RestController
public class Controller {


    private final ReceitaService receitaService;
    private final UsuarioService usuarioService;

    @Autowired
    public Controller(ReceitaService receitaService, UsuarioService usuarioService) {
        this.receitaService = receitaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/receita/api/v1")
    public ResponseEntity<ApiError> createNewReceita(@Valid @RequestBody Receita receita) throws Exception {
        return this.receitaService.createNewReceita(receita);
    }

    @GetMapping("/receitas/api/v1")
    public Iterable<Receita> getAllReceitas() {
        return this.receitaService.getAllReceitas();
    }

    @GetMapping("/receita/{id}/api/v1")
    public Optional<Receita> getReceitaById(@PathVariable("id") Integer id) {
        return this.receitaService.getReceitaById(id);
    }

    @GetMapping("/receita/api/v1/{nome}")
    public Optional<Receita> getReceitaByNome(@PathVariable("nome") String nome) {
        return this.receitaService.getReceitaByNome(nome);
    }

    @PutMapping("/receita/{id}/api/v1")
    public ResponseEntity<ApiError> updateReceita(@PathVariable("id") Integer id, @RequestBody Receita p) {
        return this.receitaService.updateReceita(id, p);
    }

    @DeleteMapping("/receita/{id}/api/v1")
    public ResponseEntity<ApiError> deleteReceita(@PathVariable("id") Integer id) {
        return this.receitaService.deleteReceita(id);
    }

    @PostMapping("/usuario/api/v1")
    public ResponseEntity<ApiError> createNewUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
        return this.usuarioService.createNewusuario(usuario);
    }

    @GetMapping("/usuarios/api/v1")
    public Iterable<Usuario> getAllUsuarios() {
        return this.usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuario/{id}/api/v1")
    public Optional<Usuario> getUsuarioById(@PathVariable("id") Integer id) {
        return this.usuarioService.getUsuarioById(id);
    }

    @GetMapping("/usuario/api/v1/{email}")
    public Optional<Usuario> getUsuarioByEmail(@PathVariable("email") String email) {
        return this.usuarioService.getUsuarioByEmail(email);
    }

    @PutMapping("/usuario/{id}/api/v1")
    public ResponseEntity<ApiError> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario p) {
        return this.usuarioService.updateUsuario(id, p);
    }

    @DeleteMapping("/usuario/{id}/api/v1")
    public ResponseEntity<ApiError> deleteUsuario(@PathVariable("id") Integer id) {
        return this.usuarioService.deleteusuario(id);
    }
}
