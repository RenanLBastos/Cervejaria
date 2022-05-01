package com.example.cervejaria.Controllers;

import com.example.cervejaria.Entities.Receita;
import com.example.cervejaria.Repository.ReceitaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@RestController
public class ReceitaController {

    private final ReceitaRepository receitaRepository;


    public ReceitaController(final ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @GetMapping("/receitas/api/v1")
    public Iterable<Receita> getAllReceitas() {
        return this.receitaRepository.findAll();
    }

    @GetMapping("/receita/{id}/api/v1")
    public Optional<Receita> getReceitaById(@PathVariable("id") Integer id) {
        return this.receitaRepository.findById(id);
    }

    @PostMapping("/receita/api/v1")
    public Receita createNewReceita(@RequestBody Receita receita) {
        Receita receita1 = new Receita();
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (receita.getNomeDaCerveja().equals(receitaObj.getNomeDaCerveja())) {
                receita1.setNomeDaCerveja("Cerveja ja cadastrada");
                return receita1;
            }
        }
        return this.receitaRepository.save(receita);
    }

    @GetMapping("/receita/api/v1/{nome}")
    public Optional<Receita> getReceitaByNome (@PathVariable("nome") String nome) {
        Receita receita1 = new Receita();
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (nome.toLowerCase(Locale.ROOT).equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT))) {
                return getReceitaById(receitaObj.getId());
            }
        }
        return Optional.empty();
    }

    @PutMapping("/receita/{id}/api/v1")
    public Receita updateReceita(@PathVariable("id") Integer id, @RequestBody Receita p) {

        Optional<Receita> receitaToUpdateOptional = this.receitaRepository.findById(id);
        if (null != receitaToUpdateOptional && !receitaToUpdateOptional.isPresent()) {
            return null;
        }
        Receita receitaToUpdate = receitaToUpdateOptional.get();
        if (p.getAbv() != null) {
            receitaToUpdate.setAbv(p.getAbv());
        }
        //TODO falta criar o outros métodos
        return receitaToUpdate;
    }

    @DeleteMapping("/receita/{id}/api/v1")
    public Receita deleteReceita(@PathVariable("id") Integer id) {
        Optional<Receita> receitaToDeleteOptional =
                this.receitaRepository.findById(id);
        if (!receitaToDeleteOptional.isPresent()) {
            return null;
        }
        Receita receita = receitaToDeleteOptional.get();
        this.receitaRepository.delete(receita);
        return receita;
    }
}
