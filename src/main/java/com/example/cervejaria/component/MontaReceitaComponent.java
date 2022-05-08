package com.example.cervejaria.component;

import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.request.ReceitaRequest;
import org.springframework.stereotype.Component;

@Component
public class MontaReceitaComponent {

    public Receita montaReceitaRequest(ReceitaRequest receitaRequest) {
        Receita novaReceita = new Receita();
        novaReceita.setNomeDaCerveja(receitaRequest.getNomeDaCerveja());
        novaReceita.setLitros(receitaRequest.getLitros());
        novaReceita.setAbv(receitaRequest.getAbv());
        novaReceita.setIbu(receitaRequest.getIbu());
        novaReceita.setOg(receitaRequest.getOg());
        novaReceita.setFg(receitaRequest.getFg());
        novaReceita.setCor(receitaRequest.getCor());
        novaReceita.setFamilia(receitaRequest.getFamilia());
        novaReceita.setIngredienteList(receitaRequest.getIngredientes());
        novaReceita.setMostura(receitaRequest.getMostura());
        novaReceita.setFervuraList(receitaRequest.getFervura());
        novaReceita.setFermentacaoMaturacaoList(receitaRequest.getFermentacaoMaturacao());
        novaReceita.setEnvase(receitaRequest.getEnvase());
        return novaReceita;
    }
}
