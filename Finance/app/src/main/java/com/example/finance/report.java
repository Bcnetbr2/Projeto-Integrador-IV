package com.example.finance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class report extends Activity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    Button btnFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
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

        btnFiltro = (Button) findViewById(R.id.btnFiltro);
        btnFiltro.setOnClickListener(this);
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
        else if (v == btnFiltro){
            Intent filtro = new Intent(this, Filtro.class);
            startActivity(filtro);
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
