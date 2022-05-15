package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Ingredientes da receita, Maltes, Lupulos, Pastilhas, Frutas
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ingrediente")
    @Column(name = "ingrediente", nullable = false)
    private String ingredientes;

    //Quantida, peso, KG, g, pacote
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "quantidade", nullable = false)
    private String quantidade;

}
