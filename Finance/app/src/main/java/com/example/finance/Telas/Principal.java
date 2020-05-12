package com.example.finance.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.R;
import com.example.finance.configDaos.UsuarioDao;
import com.example.finance.entidades.Usuario;

import java.util.List;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgReport;
    ToggleButton tgProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

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
    }

    private void entrarAdicionar(){
        Intent adicionarCategoria = new Intent(this, Lancamento.class);
        startActivity(adicionarCategoria);
    }

    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
}