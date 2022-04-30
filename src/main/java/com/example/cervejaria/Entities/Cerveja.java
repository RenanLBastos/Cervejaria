package com.example.cervejaria.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CERVEJA")
public class Cerveja {

    //Mapeia meu objeto id com a tabela id do banco de dados
    @Id
    //Não mostra responses nulos
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //Gera os ids autoincremental identicicando a coluna;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nome", nullable = false)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "abv", nullable = false)
    private String abv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "ibu", nullable = false)
    private String ibu;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "og", nullable = false)
    private String og;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "fg", nullable = false)
    private String fg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "cor", nullable = false)
    private String cor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "familia_id", referencedColumnName = "id")
    private Familia familia;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //Mapeamento um para um. Em cascata, se alguma cerveja for deletado, todas as colunas relacionadas ao id também serão
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "complexo_id", referencedColumnName = "id")
    private Complexo complexo;
}
