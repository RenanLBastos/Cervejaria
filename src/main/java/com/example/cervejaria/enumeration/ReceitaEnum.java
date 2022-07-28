package com.example.cervejaria.enumeration;

public enum ReceitaEnum {

    RECEITA_NOT_FOUND("Receita Not Found"),
    USUARIO("Usuário "),

    NOME_DA_CERVEJA_OBRIGATORIO("Nome da cerveja Obrigatório"),

    RECEITA_JA_CADASTRADA("Receita ja cadastrada"),
    USUARIO_NOT_FOUND("Usuário Not Found"),

    ENDPOINT("C:\\Users\\reina\\OneDrive\\Documentos\\endpoint.txt"),
    BUCKETNAME("C:\\Users\\reina\\OneDrive\\Documentos\\bucketName.txt"),
    ACCESSKEY("C:\\Users\\reina\\OneDrive\\Documentos\\accessKey.txt"),
    SECRETKEY("C:\\Users\\reina\\OneDrive\\Documentos\\secretKey.txt");




    private final String name;
    ReceitaEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
