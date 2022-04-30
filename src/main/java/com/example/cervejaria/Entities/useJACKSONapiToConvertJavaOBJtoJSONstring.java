package com.example.cervejaria.Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class useJACKSONapiToConvertJavaOBJtoJSONstring {
    public static void main(String[] args) {
        Receita receita = new Receita();
        receita.setNomeDaCerveja("");
        receita.setLitros("");
        receita.setAbv("");
        receita.setIbu("");
        receita.setOg("");
        receita.setFg("");
        receita.setCor("");
        Familia familia = new Familia();
        familia.setNome("");
        receita.setFamilia(familia);
        Complexo complexo = new Complexo();
        complexo.setName("");
        receita.setComplexo(complexo);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(receita);
            //System.out.println("ResultingJSONstring = " + json);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        class Receita {
        }
        ;
    }
}
//        class Receita }