package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fervura")
public class Fervura {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Tempo inicial, meio e fim
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "tempo", nullable = false)
    private String tempo;

    //Quantidade de ingrediente a ser usado
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "qtdIngrediente", nullable = false)
    private String qtdIngrediente;
}
