package com.example.finance.consulta;

import java.util.Date;

public class ObjetoConsultaMes {

    private String mesAno;
    private float valorTotalGasto;
    private String tipo;

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public float getValorTotalGasto() {
        return valorTotalGasto;
    }

    public void setValorTotalGasto(float valorTotalGasto) {
        this.valorTotalGasto = valorTotalGasto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
