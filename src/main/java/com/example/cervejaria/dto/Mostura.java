package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "mostura")
public class Mostura {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Temperatura durante o tempo de mostura
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "temperaturaRecomendada", nullable = false)
    private String temperaturaRecomendada;

    //Tempo da mostura
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "tempo", nullable = false)
    private String tempo;

    //Temperatura inicial da água sem os grãos
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "temperaturaInicial", nullable = false)
    private String temperaturaInicial;

    //Litros iniciais
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "litrosInicial", nullable = false)
    private String litrosInicial;

    //Litros para lavagem dos grãos
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "litrosLavagem", nullable = false)
    private String litrosLavagem;

    //Temperatuda da água para lavegem dos grãos
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "temperaturaLavagem", nullable = false)
    private String temperaturaLavagem;
}
