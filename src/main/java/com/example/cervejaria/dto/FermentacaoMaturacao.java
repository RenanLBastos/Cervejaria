package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fermentacaomaturacao")
public class FermentacaoMaturacao {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //Dia da fermentação
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dia", nullable = false)
    private String dia;

    //Temperatura a ser mantida
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "temperatura", nullable = false)
    private String temperatura;
}
