package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "envase")
public class Envase {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Quantidade de açúcar por Litro
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "qtdPorLitros", nullable = false)
    private String qtdPorLitros;

    //Quantidade de dias de primming
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dias", nullable = false)
    private String dias;
}
