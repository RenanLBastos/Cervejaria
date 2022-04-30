package com.example.cervejaria.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "RECEITA")
public class Receita {

    //Mapeia meu objeto id com a tabela id do banco de dados
    @Id
    //Não mostra responses nulos
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //Gera os ids autoincremental identicicando a coluna;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "NOME", nullable = false)
    private String nomeDaCerveja;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "LITROS", nullable = false)
    private String litros;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ABV", nullable = false)
    private String abv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "IBU", nullable = false)
    private String ibu;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "OG", nullable = false)
    private String og;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "FG", nullable = false)
    private String fg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "COR", nullable = false)
    private String cor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAMILIA_ID", referencedColumnName = "ID")
    private Familia familia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //Mapeamento um para um. Em cascata, se alguma cerveja for deletado, todas as colunas relacionadas ao id também serão
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPLEXO_ID", referencedColumnName = "ID")
    private Complexo complexo;
}