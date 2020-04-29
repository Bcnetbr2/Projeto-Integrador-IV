package com.example.finance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Filtro extends Activity implements View.OnClickListener{

    RadioButton rbHoje;
    RadioButton rbSemana;
    RadioButton rbMes;
    RadioButton rbAno;
    RadioButton rbSuper;
    RadioButton rbBanco;
    RadioButton rbLuz;
    RadioButton rbInternet;
    Button btnAdicionarFiltro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro);
        variaveis();


    }

    private void variaveis(){
        rbHoje = (RadioButton) findViewById(R.id.rbHoje);
        rbSemana = (RadioButton) findViewById(R.id.rbSemana);
        rbMes = (RadioButton) findViewById(R.id.rbMes);
        rbAno = (RadioButton) findViewById(R.id.rbAno);
        rbSuper = (RadioButton) findViewById(R.id.rbSuper);
        rbBanco = (RadioButton) findViewById(R.id.rbBanco);
        rbLuz = (RadioButton) findViewById(R.id.rbLuz);
        rbInternet = (RadioButton) findViewById(R.id.rbInternet);

        btnAdicionarFiltro = (Button) findViewById(R.id.btnAdicionarFiltro);
        btnAdicionarFiltro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarFiltro){
            Toast.makeText(this, "Filtro adicionado com sucesso", Toast.LENGTH_LONG).show();
        }

    }
}
