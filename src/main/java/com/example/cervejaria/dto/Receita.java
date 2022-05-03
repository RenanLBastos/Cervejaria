package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @NotNull
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


    @JsonProperty("ingredientes")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INGREDIENTE_ID", referencedColumnName = "ID")
    private List<Ingrediente> ingredienteList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOSTURA_ID", referencedColumnName = "ID")
    private Mostura mostura;

    @JsonProperty("fervura")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FERVURA_ID", referencedColumnName = "ID")
    private List<Fervura> fervuraList;

    @JsonProperty("fermentacaoMaturacao")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FERMENTACAOMATURACAO_ID", referencedColumnName = "ID")
    private List<FermentacaoMaturacao> fermentacaoMaturacaoList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENVASE_ID", referencedColumnName = "ID")
    private Envase envase;

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv + "%";
    }
}
