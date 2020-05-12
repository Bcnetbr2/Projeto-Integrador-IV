package com.example.finance.entidades;

import java.io.Serializable;

public class Categoria implements Serializable {

    private long id;
    private Usuario usuario;
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricacao) {
        this.descricao = descricacao;
    }
}
