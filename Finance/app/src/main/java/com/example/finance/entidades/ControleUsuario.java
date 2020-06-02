package com.example.finance.entidades;

import android.app.Application;

public class ControleUsuario extends Application {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario novoUsuario) {
        usuario = novoUsuario;
    }
}
