package com.example.cervejaria.controller;


import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.service.ReceitaService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/receita")
@RestController
public class ReceitaController {

    @Autowired
    protected ApplicationEventPublisher eventPublisher;
    private final ReceitaService receitaService;

    @Autowired
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping("/api/v1")
    public ResponseEntity<Receita> createNewReceita(@RequestBody Receita receita) {
        Receita newReceita = receitaService.createNewReceita(receita);
        return new ResponseEntity<>(newReceita, HttpStatus.CREATED);
    }

    @GetMapping("/receitas/api/v1")
    public ResponseEntity<Iterable<Receita>> getAllReceitas() {

        Iterable<Receita> receita = receitaService.getAllReceitas();
        Lists.newArrayList(receita).size();
        if(Lists.newArrayList(receita).size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }

    @GetMapping("/{id}/api/v1")
    public ResponseEntity<Optional<Receita>> getReceitaById(@PathVariable("id") Integer id) {
        Optional<Receita> receita = this.receitaService.getReceitaById(id);
        if (receita.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }

    @GetMapping("/api/v1/{nome}")
    public ResponseEntity<Optional<Receita>> getReceitaByNome (@PathVariable("nome") String nome) {
        Optional<Receita> receita = this.receitaService.getReceitaByNome(nome);
        if (receita.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }

    @PutMapping("/{id}/api/v1")
    public ResponseEntity<Receita> updateReceita(@PathVariable("id") Integer id, @RequestBody Receita p) {
        Receita receita = this.receitaService.updateReceita(id, p);
        return new ResponseEntity<>(receita, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/api/v1")
    public ResponseEntity<Receita> deleteReceita(@PathVariable("id") Integer id) {
        Receita receita = this.receitaService.deleteReceita(id);
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }
}
