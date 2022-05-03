package com.example.cervejaria.service;

import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Locale;
import java.util.Optional;

@Validated
@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public Receita createNewReceita(Receita receita) throws Exception {
        Receita receita1 = new Receita();
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (receita.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim().equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim())) {
                receita1.setNomeDaCerveja("Cerveja ja cadastrada");
                return receita1;
            }
        }
        try {
            return this.receitaRepository.save(receita);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nome da cerveja Obrigatório", e);
        }
    }

    public Iterable<Receita> getAllReceitas() {
        return this.receitaRepository.findAll();
    }

    public Optional<Receita> getReceitaById(Integer id) {
        Optional<Receita> receitaRepositoryById = this.receitaRepository.findById(id);

        try {
            if (receitaRepositoryById.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                return receitaRepositoryById;
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Receita Not Found", e);
        }
    }

    public Optional<Receita> getReceitaByNome(String nome) {
        try {
            Iterable<Receita> cervejaList = getAllReceitas();
            for (Receita receitaObj : cervejaList) {
                if (nome.toLowerCase(Locale.ROOT).trim().equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim())) {
                    return getReceitaById(receitaObj.getId());
                }
            }
            throw new ResourceNotFoundException();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Receita Not Found", e);
        }
    }

    public Receita updateReceita(Integer id, @RequestBody Receita p) {

        Optional<Receita> receitaToUpdateOptional = this.receitaRepository.findById(id);
        if (receitaToUpdateOptional.isEmpty()) {
            return null;
        }
        Receita receitaToUpdate = receitaToUpdateOptional.get();
        if (p.getAbv() != null) {
            receitaToUpdate.setAbv(p.getAbv());
        }
        //TODO falta criar o outros métodos
        //TODO tratativa de erros
        //TODO criar dto usuario, service e repository
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
