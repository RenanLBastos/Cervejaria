package com.example.cervejaria.request;

import com.example.cervejaria.dto.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class ReceitaRequest {

    private String nomeDaCerveja;
    private String litros;
    private String abv;
    private String ibu;
    private String og;
    private String fg;
    private String cor;
    private Familia familia;
    private List<Ingrediente> ingredientes;
    private Mostura mostura;
    private List<Fervura> fervura;
    private List<FermentacaoMaturacao> fermentacaoMaturacao;
    private Envase envase;
}
