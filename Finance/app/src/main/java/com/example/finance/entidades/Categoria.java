package com.example.finance.entidades;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int id;
    private String descricacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricacao() {
        return descricacao;
    }

    public void setDescricacao(String descricacao) {
        this.descricacao = descricacao;
    }
}
