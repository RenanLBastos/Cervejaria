package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "RECEITA")
public class Receita {

    @Id
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


    @JsonProperty("ingredientes")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INGREDIENTE_ID", referencedColumnName = "ID")
    private List<Ingrediente> ingredienteList = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MOSTURA_ID", referencedColumnName = "ID")
    private Mostura mostura;

    @JsonProperty("fervura")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FERVURA_ID", referencedColumnName = "ID")
    private List<Fervura> fervuraList = new ArrayList<>();

    @JsonProperty("fermentacaoMaturacao")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FERMENTACAOMATURACAO_ID", referencedColumnName = "ID")
    private List<FermentacaoMaturacao> fermentacaoMaturacaoList = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENVASE_ID", referencedColumnName = "ID")
    private Envase envase;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "FOTO")
    private String foto;

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        if (abv.contains("%")) {
            this.abv = abv;
        } else {
            this.abv = abv + "%";
        }
    }
}
