package com.example.cervejaria.service;

import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Locale;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita createNewReceita(Receita receita) {
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
    public Iterable<Receita> getAllReceitas() {
        return this.receitaRepository.findAll();
    }
    public Optional<Receita> getReceitaById(Integer id) {
        return this.receitaRepository.findById(id);
    }

    public Optional<Receita> getReceitaByNome (String nome) {
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (nome.toLowerCase(Locale.ROOT).equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT))) {
                return getReceitaById(receitaObj.getId());
            }
        }
        return Optional.empty();
    }

    public Receita updateReceita(Integer id, @RequestBody Receita p) {

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

    public Receita deleteReceita(Integer id) {
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
