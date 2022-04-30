package com.example.cervejaria.Controllers;

import com.example.cervejaria.Entities.Receita;
import com.example.cervejaria.Repository.ReceitaRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/receitas/{id}/api/v1")
    public Optional<Receita> getReceitaById(@PathVariable("id") Integer id) {
        return this.receitaRepository.findById(id);
    }

    @PostMapping("/receitas/api/v1")
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

    @PutMapping("/receitas/{id}/api/v1")
    public Receita updateReceita(@PathVariable("id") Integer id, @RequestBody Receita p) {

        Optional<Receita> cervejaToUpdateOptional = this.receitaRepository.findById(id);
        if (null != cervejaToUpdateOptional && !cervejaToUpdateOptional.isPresent()) {
            return null;
        }
        Receita receitaToUpdate = cervejaToUpdateOptional.get();
        if (p.getAbv() != null) {
            receitaToUpdate.setAbv(p.getAbv());
        }
//        if (p.getQuantity() != null) {
//            cervejaToUpdate.setQuantity(p.getQuantity());
//        }
//        if (p.getNome() != null) {
//            cervejaToUpdate.setName(p.getName());
//        }
//        if (p.getWateringFrequency() != null) {
//            cervejaToUpdate.setWateringFrequency(p.getWateringFrequency());
//        }
//        Plant updatedPlant = this.cervejaRepository.save(cervejaToUpdate);
        return receitaToUpdate;
    }

    @DeleteMapping("/receitas/{id}/api/v1")
    public Receita deleteCerveja(@PathVariable("id") Integer id) {
        Optional<Receita> cervejaToDeleteOptional =
                this.receitaRepository.findById(id);
        if (!cervejaToDeleteOptional.isPresent()) {
            return null;
        }
        Receita receita = cervejaToDeleteOptional.get();
        this.receitaRepository.delete(receita);
        return receita;
    }
}
