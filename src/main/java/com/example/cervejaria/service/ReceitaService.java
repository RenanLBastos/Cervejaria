package com.example.cervejaria.service;

import com.example.cervejaria.component.MontaReceitaComponent;
import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.ReceitaRepository;
import com.example.cervejaria.request.ReceitaRequest;
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
    private MontaReceitaComponent montaReceitaComponent;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public ResponseEntity<ApiError> createNewReceita(ReceitaRequest receitaRequest) {
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (receitaRequest.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim().equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim())) {
                return new ResponseEntity<>(new ApiError(200, "Receita ja cadastrada", "create ok"), HttpStatus.OK);
            }
        }
        try {
            this.receitaRepository.save(this.montaReceitaComponent.montaReceitaRequest(receitaRequest));
            return new ResponseEntity<>(new ApiError(200, "Receita " + receitaRequest.getNomeDaCerveja() + " cadastrada com sucesso", "create ok"), HttpStatus.OK);
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
                    HttpStatus.NOT_FOUND, "${RECEITA_NOT_FOUND}", e);
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
                    HttpStatus.NOT_FOUND, "${RECEITA_NOT_FOUND}", e);
        }
    }

    public ResponseEntity<ApiError> updateReceita(Integer id, @RequestBody ReceitaRequest p) {

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

                if (p.getIngredientes() != null && !p.getIngredientes().isEmpty()) {
                    receitaToUpdate.setIngredienteList(p.getIngredientes());
                }

                if (p.getMostura() != null) {
                    receitaToUpdate.setMostura(p.getMostura());
                }

                if (p.getFervura() != null && !p.getFervura().isEmpty()) {
                    receitaToUpdate.setFervuraList(p.getFervura());
                }

                if (p.getFermentacaoMaturacao() != null && !p.getFermentacaoMaturacao().isEmpty()) {
                    receitaToUpdate.setFermentacaoMaturacaoList(p.getFermentacaoMaturacao());
                }

                if (p.getEnvase() != null) {
                    receitaToUpdate.setEnvase(p.getEnvase());
                }

                this.receitaRepository.save(montaReceitaComponent.montaReceitaRequest(p));

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
