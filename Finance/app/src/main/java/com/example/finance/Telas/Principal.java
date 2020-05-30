package com.example.finance.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.R;
import com.example.finance.entidades.Usuario;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgLancamento;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    Usuario usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        variaveis();
        receberUsuario();


}

    private void variaveis(){
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgLancamento = (ToggleButton) findViewById(R.id.tgLancamento);
        tgLancamento.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        if (v == tgLancamento){
            entrarLancamento();
        }
        else if (v == tgReport){
            entrarReport();
            }
        else if (v == tgProfile){
            entrarProfile();
        }
    }

    private void entrarLancamento(){
        //Intent adicionarCategoria = new Intent(this, Tela_Lancamento.class);
        //startActivity(adicionarCategoria);
        enviarUsuario();
    }

    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }

    private void receberUsuario(){

        if(getIntent().getExtras().getSerializable("usuario") != null){

            usuario = (Usuario)getIntent().getExtras().getSerializable("usuario");

        }
        else{
            Toast.makeText(this,"não recebido",Toast.LENGTH_LONG).show();
            Log.e("Usuario","não recebido");
        }

    }
    private void enviarUsuario(){

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(telaLancamento);

    }
}