package com.example.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class profile extends Activity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgReport;


    EditText edtRecebeNome;
    EditText edtRecebeEmail;
    EditText edtRecebeTele;
    EditText edtRecebeRenda;
    EditText edtSenhaAtual;
    EditText edtSenhaNova;
    EditText edtCofirmaNovaSenha;
    Button btnSalvarDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        variaveis();

}

    private void variaveis(){
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgAdicionar = (ToggleButton) findViewById(R.id.tgAdd);
        tgAdicionar.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        edtRecebeNome = (EditText) findViewById(R.id.edtRecebeNome);
        edtRecebeEmail = (EditText) findViewById(R.id.edtRecebeEmail);
        edtRecebeTele = (EditText) findViewById(R.id.edtRecebeTele);
        edtRecebeRenda = (EditText) findViewById(R.id.edtRecebeRenda);
        edtSenhaAtual = (EditText) findViewById(R.id.edtSenhaAtual);
        edtSenhaNova = (EditText) findViewById(R.id.edtSenhaNova);
        edtCofirmaNovaSenha = (EditText) findViewById(R.id.edtConfirmacaoSenha);

        btnSalvarDados = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnSalvarDados.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tgAdicionar){
            entrarAdicionar();
        }
        else if (v == tgReport){
            entrarReport();
        }

        else if (v == tgHome){
            entrarHome();
        }

        else if (v == btnSalvarDados){
            salvarDados();
        }
    }

    private void entrarAdicionar(){
        Intent lancamento = new Intent(this, Lancamento.class);
        startActivity(lancamento);
    }

    private void entrarReport(){
        Intent report = new Intent(this, report.class);
        startActivity(report);
    }


    private void entrarHome(){
        Intent home = new Intent(this, principal.class);
        startActivity(home);
    }
    private void salvarDados(){
        Toast.makeText(this, "Dados salvo com sucesso", Toast.LENGTH_LONG).show();
    }

}
