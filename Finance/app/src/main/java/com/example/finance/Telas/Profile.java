package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.finance.R;
import com.example.finance.configDaos.UsuarioDao;
import com.example.finance.Controle.ControleEntidades;

public class Profile extends Activity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgReport;


    EditText edtRecebeNome;
    EditText edtRecebeEmail;
    EditText edtRecebeTele;
    EditText edtRecebeRenda;
    EditText edtSenhaAtual;
    EditText edtSenhaNova;

    Button btnSalvarDados;


    UsuarioDao usuarioDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        variaveis();
        //receberUsuario();
        preencherDados();

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
        //edtCofirmaNovaSenha = (EditText) findViewById(R.id.edtConfirmacaoSenha);

        btnSalvarDados = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnSalvarDados.setOnClickListener(this);
        usuarioDao = new UsuarioDao(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tgAdicionar){
            entrarAdicionar();
            finish();
        }
        else if (v == tgReport){
            entrarReport();
            finish();
        }

        else if (v == tgHome){
            entrarHome();
            finish();
        }

        else if (v == btnSalvarDados){
            salvarDados();
            finish();
        }
    }

    private void entrarAdicionar(){
        Intent lancamento = new Intent(this, Tela_Lancamento.class);
        startActivity(lancamento);
    }

    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);
    }


    private void entrarHome(){
        Intent home = new Intent(this, Principal.class);
        startActivity(home);
        //finish();
    }
    private void salvarDados(){
        ControleEntidades.getUsuario().setLogin(edtRecebeNome.getText().toString());
        ControleEntidades.getUsuario().setEmail(edtRecebeEmail.getText().toString());
        ControleEntidades.getUsuario().setFone(edtRecebeTele.getText().toString());
        ControleEntidades.getUsuario().setRenda(Float.parseFloat(edtRecebeRenda.getText().toString()));
        if(edtSenhaNova.getText().toString().equals("")){
            ControleEntidades.getUsuario().setSenha(edtSenhaAtual.getText().toString());
        }
        else{
            ControleEntidades.getUsuario().setSenha(edtSenhaNova.getText().toString());
        }

        long id =  usuarioDao.alterar(ControleEntidades.getUsuario());

        Toast.makeText(this, "Dados salvo com sucesso", Toast.LENGTH_LONG).show();
    }

    public void preencherDados(){

        edtRecebeNome.setText(ControleEntidades.getUsuario().getLogin());
        edtRecebeEmail.setText(ControleEntidades.getUsuario().getEmail());
        edtRecebeTele.setText(ControleEntidades.getUsuario().getFone());
        edtRecebeRenda.setText(String.valueOf(ControleEntidades.getUsuario().getRenda()));
        edtSenhaAtual.setText(ControleEntidades.getUsuario().getSenha());



    }

}
