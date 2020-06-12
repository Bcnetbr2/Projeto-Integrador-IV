package com.example.finance.controle;

import android.app.Application;

import com.example.finance.entidades.Categoria;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.util.Date;

public class ControleEntidades extends Application {

    private static Usuario usuario;
    private static Lancamento lancamento;
    private static String Status = "vazio";
    private static Categoria categoria;
    private static Date dataInicial;
    private static Date dataFinal;
    private static String statusFiltro = "inativo";

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario novoUsuario) {
        usuario = novoUsuario;
    }

    public static Lancamento getLancamento() {
        return lancamento;
    }

    public static void setLancamento(Lancamento novoLancamento) {
        lancamento = novoLancamento;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        Status = status;
    }

    public static Categoria getCategoria() {
        return categoria;
    }

    public static void setCategoria(Categoria novoCategoria) {
        categoria = novoCategoria;
    }

    public static Date getDataInicial() {
        return dataInicial;
    }

    public static void setDataInicial(Date data) {
        dataInicial = data;
    }

    public static Date getDataFinal() {
        return dataFinal;
    }

    public static void setDataFinal(Date data) {
        dataFinal = data;
    }

    public static String getStatusFiltro() {
        return statusFiltro;
    }

    public static void setStatusFiltro(String novoStatus2) {
        statusFiltro = novoStatus2;
    }
}
