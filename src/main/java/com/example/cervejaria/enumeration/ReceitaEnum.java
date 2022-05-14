package com.example.cervejaria.enumeration;

public enum ReceitaEnum {

    RECEITA_NOT_FOUND("Receita Not Found"),
    USUARIO("Usuário "),

    NOME_DA_CERVEJA_OBRIGATORIO("Nome da cerveja Obrigatório"),

    RECEITA_JA_CADASTRADA("Receita ja cadastrada"),
    USUARIO_NOT_FOUND("Usuário Not Found");

    private String name;
    ReceitaEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
