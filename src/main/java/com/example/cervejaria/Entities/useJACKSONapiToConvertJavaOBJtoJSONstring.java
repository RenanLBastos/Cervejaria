package com.example.cervejaria.Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class useJACKSONapiToConvertJavaOBJtoJSONstring {
    public static void main(String[] args) {
        Receita receita = new Receita();
        receita.setNomeDaCerveja("Lucky Red");
        receita.setLitros("20");
        receita.setAbv("4,4");
        receita.setIbu("22");
        receita.setOg("1044");
        receita.setFg("1010");
        receita.setCor("13");
        Familia familia = new Familia();
        familia.setNome("Irish Red Ale");
        receita.setFamilia(familia);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setIngrediente("Malte Pilsen");
        ingrediente.setQuantidade("3Kg");
        Ingrediente ingrediente1 = new Ingrediente();
        ingrediente1.setIngrediente("Malte Munique Light");
        ingrediente1.setQuantidade("1Kg");
        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setIngrediente("Malte Caramel 150");
        ingrediente2.setQuantidade("300g");
        Ingrediente ingrediente3 = new Ingrediente();
        ingrediente3.setIngrediente("Malte Black");
        ingrediente3.setQuantidade("60g");
        Ingrediente ingrediente4 = new Ingrediente();
        ingrediente4.setIngrediente("Lúpulo Admiral");
        ingrediente4.setQuantidade("20g");
        Ingrediente ingrediente5 = new Ingrediente();
        ingrediente5.setIngrediente("Pastilha Whirfloc");
        ingrediente5.setQuantidade("1");
        Ingrediente ingrediente6 = new Ingrediente();
        ingrediente6.setIngrediente("Levedura S-04");
        ingrediente6.setQuantidade("1");
        List <Ingrediente> ingredientesList= new ArrayList<>();
        ingredientesList.add(ingrediente);
        ingredientesList.add(ingrediente1);
        ingredientesList.add(ingrediente2);
        ingredientesList.add(ingrediente3);
        ingredientesList.add(ingrediente4);
        ingredientesList.add(ingrediente5);
        ingredientesList.add(ingrediente6);
        receita.setIngredienteList(ingredientesList);

        Mostura mostura = new Mostura();
        mostura.setTemperaturaRecomendada("69");
        mostura.setTempo("60");
        mostura.setTemperaturaInicial("74");
        mostura.setLitrosLavagem("10");
        mostura.setLitrosInicial("20");
        mostura.setTemperaturaLavagem("70");
        receita.setMostura(mostura);

        Fervura fervura = new Fervura();
        fervura.setTempo("60");
        fervura.setQtdIngrediente("10g Admiral");
        Fervura fervura1 = new Fervura();
        fervura1.setTempo("30");
        fervura1.setQtdIngrediente("#");
        Fervura fervura2 = new Fervura();
        fervura2.setTempo("5");
        fervura2.setQtdIngrediente("10g Admiral");
        Fervura fervura3 = new Fervura();
        fervura3.setTempo("5");
        fervura3.setQtdIngrediente("10g Admiral");
        Fervura fervura4 = new Fervura();
        fervura4.setTempo("0");
        fervura4.setQtdIngrediente("#");
        List <Fervura> fervuraList = new ArrayList<>();
        fervuraList.add(fervura);
        fervuraList.add(fervura1);
        fervuraList.add(fervura2);
        fervuraList.add(fervura3);
        fervuraList.add(fervura4);
        receita.setFervuraList(fervuraList);

        FermentacaoMaturacao fermentacaoMaturacao = new FermentacaoMaturacao();
        fermentacaoMaturacao.setDia("0");
        fermentacaoMaturacao.setTemperatura("20");
        FermentacaoMaturacao fermentacaoMaturacao1 = new FermentacaoMaturacao();
        fermentacaoMaturacao1.setDia("10");
        fermentacaoMaturacao1.setTemperatura("20");
        FermentacaoMaturacao fermentacaoMaturacao2 = new FermentacaoMaturacao();
        fermentacaoMaturacao2.setDia("11");
        fermentacaoMaturacao2.setTemperatura("5");
        FermentacaoMaturacao fermentacaoMaturacao3 = new FermentacaoMaturacao();
        fermentacaoMaturacao3.setDia("14");
        fermentacaoMaturacao3.setTemperatura("5");
        List<FermentacaoMaturacao> fermentacaoMaturacaoList = new ArrayList<>();
        fermentacaoMaturacaoList.add(fermentacaoMaturacao);
        fermentacaoMaturacaoList.add(fermentacaoMaturacao1);
        fermentacaoMaturacaoList.add(fermentacaoMaturacao2);
        fermentacaoMaturacaoList.add(fermentacaoMaturacao3);
        receita.setFervuraList(fervuraList);

        Envase envase = new Envase();
        envase.setQtdPorLitros("5g");
        envase.setDias("10");
        receita.setEnvase(envase);

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