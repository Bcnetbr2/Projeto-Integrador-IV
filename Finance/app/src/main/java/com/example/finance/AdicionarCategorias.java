package com.example.finance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarCategorias extends Activity implements View.OnClickListener {


    EditText edtDescricao;
    Button btnAdicionarCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionarcategorias);
        variaveis();

    }

    private void variaveis(){
        edtDescricao = (EditText) findViewById(R.id.edtNomeFornecedor);

        btnAdicionarCateg = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionarCateg.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarCateg){
            adicionarCateg();
        }

    }

    private void adicionarCateg(){
        Toast.makeText(this, "Foi adicionado a categoria com sucecsso", Toast.LENGTH_LONG).show();
    }

    }

