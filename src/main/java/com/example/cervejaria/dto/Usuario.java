package com.example.cervejaria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "NOME", nullable = false)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "TELEFONE", nullable = false)
    private String telefone;
}
