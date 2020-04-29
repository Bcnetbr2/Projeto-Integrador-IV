package com.example.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Categorias extends Activity implements View.OnClickListener {

    Button btnBanco;
    Button btnLuz;
    Button btnAgua;
    Button btnGas;
    Button btnSuper;
    Button btnEstudos;
    Button btnAdicionarMais;
    Button btnOp;

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgReport;
    ToggleButton tgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorias);
        variaveis();

    }

    private void variaveis(){
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgAdicionar = (ToggleButton) findViewById(R.id.tgAdd);
        tgAdicionar.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

        btnBanco = (Button) findViewById(R.id.btnBanco);
        btnBanco.setOnClickListener(this);

        btnLuz = (Button) findViewById(R.id.btnLuz);
        btnLuz.setOnClickListener(this);

        btnAgua = (Button) findViewById(R.id.btnAgua);
        btnAgua.setOnClickListener(this);

        btnGas = (Button) findViewById(R.id.btnGas);
        btnGas.setOnClickListener(this);

        btnSuper = (Button) findViewById(R.id.btnSuper);
        btnSuper.setOnClickListener(this);

        btnEstudos = (Button) findViewById(R.id.btnEstudos);
        btnEstudos.setOnClickListener(this);

        btnOp = (Button) findViewById(R.id.btnOperadora);
        btnOp.setOnClickListener(this);

        btnAdicionarMais = (Button) findViewById(R.id.btnAdicionarMais);
        btnAdicionarMais.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tgAdicionar){
            entrarAdicionar();
        }
        else if (v == tgReport){
            entrarReport();
        }
        else if (v == tgProfile){
            entrarProfile();
        }
        else if (v == tgHome){
            entrarHome();
        }
        else if (v == btnBanco){
            Toast.makeText(this, "Adicionou conta de banco", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnLuz){
            Toast.makeText(this, "Adicionou conta de luz", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnAgua){
            Toast.makeText(this, "Adicionou conta de agua", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnEstudos){
            Toast.makeText(this, "Adicionou conta de estudos", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnGas){
            Toast.makeText(this, "Adicionou conta de gás", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnSuper){
            Toast.makeText(this, "Adicionou conta de supermecado", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnOp){
            Toast.makeText(this, "Adicionou conta de operadora", Toast.LENGTH_SHORT).show();
        }
        else if (v == btnAdicionarMais){
            Intent telaAdicionar = new Intent(this, AdicionarCategoria.class);
            startActivity(telaAdicionar);
        }
    }

    private void entrarAdicionar(){
        Intent adicionar = new Intent(this, Categorias.class);
        startActivity(adicionar);
    }

    private void entrarReport(){
        Intent report = new Intent(this, report.class);
        startActivity(report);
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, profile.class);
        startActivity(profile);
    }
    private void entrarHome(){
        Intent home = new Intent(this, principal.class);
        startActivity(home);
    }
}
