package com.example.cervejaria.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.cervejaria.component.MontaReceitaComponent;
import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.enumeration.ReceitaEnum;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.exception.ResourceNotFoundException;
import com.example.cervejaria.repository.ReceitaRepository;
import com.example.cervejaria.request.ReceitaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Validated
@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    private final MontaReceitaComponent montaReceitaComponent;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository, MontaReceitaComponent montaReceitaComponent) {
        this.receitaRepository = receitaRepository;
        this.montaReceitaComponent = montaReceitaComponent;
    }

    public ResponseEntity<ApiError> createNewReceita(ReceitaRequest receitaRequest, MultipartFile multipartFile) {
        Iterable<Receita> cervejaList = getAllReceitas();
        for (Receita receitaObj : cervejaList) {
            if (receitaRequest.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim().equals(receitaObj.getNomeDaCerveja().toLowerCase(Locale.ROOT).trim())) {
                return new ResponseEntity<>(new ApiError(200, ReceitaEnum.RECEITA_JA_CADASTRADA.getName(), "create ok"), HttpStatus.OK);
            }
        }
        try {
            this.receitaRepository.save(this.montaReceitaComponent.montaReceitaRequest(receitaRequest, multipartFile));
            return new ResponseEntity<>(new ApiError(200, "Receita " + receitaRequest.getNomeDaCerveja() + " cadastrada com sucesso", "create ok"), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ReceitaEnum.NOME_DA_CERVEJA_OBRIGATORIO.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public Iterable<Receita> getAllReceitas() {
        return this.receitaRepository.findAll();
    }

    public Optional<Receita> getReceitaById(long id) {
        Optional<Receita> receitaRepositoryById = this.receitaRepository.findById((int) id);

        try {
            if (receitaRepositoryById.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                return receitaRepositoryById;
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, ReceitaEnum.RECEITA_NOT_FOUND.getName(), e);
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
                    HttpStatus.NOT_FOUND, ReceitaEnum.RECEITA_NOT_FOUND.getName(), e);
        }
    }

    public ResponseEntity<ApiError> updateReceita(Integer id, ReceitaRequest p, MultipartFile multipartFile) throws IOException {

        Optional<Receita> receitaToUpdateOptional = this.receitaRepository.findById(id);
        try {
            if (receitaToUpdateOptional.isEmpty()) {
                throw new ResourceNotFoundException();
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND, ReceitaEnum.RECEITA_NOT_FOUND.getName(), e);
        }
        Receita receitaToUpdate = receitaToUpdateOptional.get();


        if (p.getNomeDaCerveja() != null) {
            receitaToUpdate.setNomeDaCerveja(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getNomeDaCerveja());
        }

        if (p.getLitros() != null) {
            receitaToUpdate.setLitros(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getLitros());
        }

        if (p.getAbv() != null) {
            receitaToUpdate.setAbv(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getAbv());
        }

        if (p.getIbu() != null) {
            receitaToUpdate.setIbu(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getIbu());
        }

        if (p.getOg() != null) {
            receitaToUpdate.setOg(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getOg());
        }

        if (p.getFg() != null) {
            receitaToUpdate.setFg(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getFg());
        }

        if (p.getCor() != null) {
            receitaToUpdate.setCor(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getCor());
        }

        if (p.getFamilia() != null) {
            receitaToUpdate.setFamilia(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getFamilia());
        }

        if (!p.getFervura().isEmpty()) {
            receitaToUpdate.setIngredienteList(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getIngredienteList());
        }

        if (!p.getFervura().isEmpty()) {
            receitaToUpdate.setMostura(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getMostura());
        }

        if (!p.getFervura().isEmpty()) {
            receitaToUpdate.setFervuraList(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getFervuraList());
        }

        if (!p.getFermentacaoMaturacao().isEmpty()) {
            receitaToUpdate.setFermentacaoMaturacaoList(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getFermentacaoMaturacaoList());
        }

        if (p.getEnvase() != null) {
            receitaToUpdate.setEnvase(this.montaReceitaComponent.montaReceitaRequest(p, multipartFile).getEnvase());
        }

        this.receitaRepository.save(receitaToUpdate);

        return new ResponseEntity<>(new ApiError(200, "Receida da " + receitaToUpdate.getNomeDaCerveja() + " atualizada com sucesso", "put ok"), HttpStatus.OK);


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
                    HttpStatus.NOT_FOUND, ReceitaEnum.RECEITA_NOT_FOUND.getName(), e);
        }
    }


}
