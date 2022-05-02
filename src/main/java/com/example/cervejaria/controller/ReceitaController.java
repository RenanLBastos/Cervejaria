package com.example.cervejaria.controller;


import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.service.ReceitaService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/receita")
@RestController
public class ReceitaController {


    private final ReceitaService receitaService;

    @Autowired
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/api/v1")
    public Receita createNewReceita(@Valid @RequestBody Receita receita) throws Exception {
        return this.receitaService.createNewReceita(receita);

    }

    @GetMapping("/receitas/api/v1")
    public Iterable<Receita> getAllReceitas() {
        return this.receitaService.getAllReceitas();
    }

    @GetMapping("/{id}/api/v1")
    public Optional<Receita> getReceitaById(@PathVariable("id") Integer id) {
        return this.receitaService.getReceitaById(id);
    }

    @GetMapping("/api/v1/{nome}")
    public Optional<Receita> getReceitaByNome(@PathVariable("nome") String nome) {
        return this.receitaService.getReceitaByNome(nome);
    }

    @PutMapping("/{id}/api/v1")
    public Receita updateReceita(@PathVariable("id") Integer id, @RequestBody Receita p) {
        return this.receitaService.updateReceita(id, p);
    }

    @DeleteMapping("/{id}/api/v1")
    public Receita deleteReceita(@PathVariable("id") Integer id) {
        return this.receitaService.deleteReceita(id);
    }
}
