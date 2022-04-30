package com.example.Plant.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CERVEJA")
public class Cerveja {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    @EmbeddedId
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "abv", nullable = false)
    private String abv;

    @Column(name = "ibu", nullable = false)
    private String ibu;

    @Column(name = "og", nullable = false)
    private String og;

    @Column(name = "fg", nullable = false)
    private String fg;

    @Column(name = "cor", nullable = false)
    private String cor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "familia_id")
    private Familia familia;
}
