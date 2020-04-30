package com.example.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Lancamento extends Activity implements View.OnClickListener{

    EditText edtAddCategoria;
    EditText edtAddValorGasto;
    Button btnAdicionar;
    Spinner spTipos;
    Button btnAdicionarMais;
    ToggleButton tgHome;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    TextView tvAdicionarCateg;
    TextView tvFornecedor;
    Button btnAdicionarLancamento;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lancamento);
        variaveis();



    }

    public void variaveis(){


        btnAdicionar = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionar.setOnClickListener(this);

        spTipos = (Spinner) findViewById(R.id.spTipos);
        String[] lsPeso = getResources().getStringArray(R.array.tipo);


        tvAdicionarCateg= (TextView) findViewById(R.id.tvAdicionarCateg);
        tvAdicionarCateg.setOnClickListener(this);

        tvFornecedor = (TextView) findViewById(R.id.tvAdicionarFornecedor);
        tvFornecedor.setOnClickListener(this);

        btnAdicionarLancamento = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionarLancamento.setOnClickListener(this);

        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionar){
        Toast.makeText(this, "Adicionou uma nova categoria", Toast.LENGTH_SHORT).show();
        }

        else if (v == tvAdicionarCateg){
            Intent telaAdicionar = new Intent(this, AdicionarCategorias.class);
            startActivity(telaAdicionar);
        }
         else if (v == tgReport){
            entrarReport();
        }
        else if (v == tgProfile){
            entrarProfile();
        }
        else if (v == tvFornecedor){
            entrarFornecedor();
        }
        else if (v == tgHome){
            entrarHome();
        }

        else if (v == btnAdicionarLancamento){
            adicionarLancamento();
        }
    }


    private void entrarReport(){
        Intent report = new Intent(this, report.class);
        startActivity(report);
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, profile.class);
        startActivity(profile);
    }
    private void entrarFornecedor(){
        Intent profile = new Intent(this, Fornecedor.class);
        startActivity(profile);
    }

    private void entrarHome(){
        Intent home = new Intent(this, principal.class);
        startActivity(home);
    }
    private void adicionarLancamento(){
        Toast.makeText(this, "Lançamento realizado com sucesso", Toast.LENGTH_LONG).show();
    }
  }

