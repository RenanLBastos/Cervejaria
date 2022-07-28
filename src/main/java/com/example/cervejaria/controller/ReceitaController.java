package com.example.cervejaria.controller;


import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.request.ReceitaRequest;
import com.example.cervejaria.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;


@RestController
public class ReceitaController {


    private final ReceitaService receitaService;

    @Autowired
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/receita/api/v1")
    public ResponseEntity<ApiError> createNewReceita(@Valid @RequestPart(value = "request") ReceitaRequest receitaRequest, @RequestPart(value = "file") MultipartFile file) {
        return this.receitaService.createNewReceita(receitaRequest, file);
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
    public ResponseEntity<ApiError> updateReceita(@PathVariable("id") Integer id, @RequestBody ReceitaRequest p, @RequestPart(value = "file") MultipartFile file) {
        return this.receitaService.updateReceita(id, p, file);
    }

    @DeleteMapping("/receita/{id}/api/v1")
    public ResponseEntity<ApiError> deleteReceita(@PathVariable("id") Integer id) {
        return this.receitaService.deleteReceita(id);
    }
}
