package com.example.cervejaria.service;

import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

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

    public ResponseEntity<ApiError> createNewReceita(Receita receita) throws Exception {
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (receita.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim().equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim())) {
                return new ResponseEntity<>(new ApiError(200, "Receita ja cadastrada", "create ok"), HttpStatus.OK);
            }
        }
        try {
            this.receitaRepository.save(receita);
            return new ResponseEntity<>(new ApiError(200, "Receita " + receita.getNomeDaCerveja() + " cadastrada com sucesso", "create ok"), HttpStatus.OK);
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

    public ResponseEntity<ApiError> updateReceita(Integer id, @RequestBody Receita p) {

        Optional<Receita> receitaToUpdateOptional = this.receitaRepository.findById(id);
        try {
            if (receitaToUpdateOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                Receita receitaToUpdate = receitaToUpdateOptional.get();

                if (p.getNomeDaCerveja() != null) {
                    receitaToUpdate.setNomeDaCerveja(p.getNomeDaCerveja());
                }

                if (p.getLitros() != null) {
                    receitaToUpdate.setLitros(p.getLitros());
                }

                if (p.getAbv() != null) {
                    receitaToUpdate.setAbv(p.getAbv());
                }

                if (p.getIbu() != null) {
                    receitaToUpdate.setIbu(p.getIbu());
                }

                if (p.getOg() != null) {
                    receitaToUpdate.setOg(p.getOg());
                }

                if (p.getFg() != null) {
                    receitaToUpdate.setFg(p.getFg());
                }

                if (p.getCor() != null) {
                    receitaToUpdate.setCor(p.getCor());
                }

                if (p.getFamilia() != null) {
                    receitaToUpdate.setFamilia(p.getFamilia());
                }

                if (p.getIngredienteList() != null && !p.getIngredienteList().isEmpty()) {
                    receitaToUpdate.setIngredienteList(p.getIngredienteList());
                }

                if (p.getMostura() != null) {
                    receitaToUpdate.setMostura(p.getMostura());
                }

                if (p.getFervuraList() != null && !p.getFervuraList().isEmpty()) {
                    receitaToUpdate.setFervuraList(p.getFervuraList());
                }

                if (p.getFermentacaoMaturacaoList() != null && !p.getFermentacaoMaturacaoList().isEmpty()) {
                    receitaToUpdate.setFermentacaoMaturacaoList(p.getFermentacaoMaturacaoList());
                }

                if (p.getEnvase() != null) {
                    receitaToUpdate.setEnvase(p.getEnvase());
                }

                this.receitaRepository.save(receitaToUpdate);

                //TODO criar dto usuario, service e repository
                return new ResponseEntity<>(new ApiError(200, "Receida da " + receitaToUpdate.getNomeDaCerveja() + " atualizada com sucesso", "put ok"), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Receita Not Found", e);
        }
    }

    public ResponseEntity<ApiError> deleteReceita(Integer id) {
        Optional<Receita> receitaToDeleteOptional = this.receitaRepository.findById(id);
        try {
            if (receitaToDeleteOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                Receita receita = receitaToDeleteOptional.get();
                this.receitaRepository.delete(receita);
                return new ResponseEntity<>(new ApiError(200, "Receida da " + receita.getNomeDaCerveja() + " deletada com sucesso", "delete ok"), HttpStatus.OK);

            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, "Receita Not Found", e);
        }
    }


}
