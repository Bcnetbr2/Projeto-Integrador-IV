package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entrada extends AppCompatActivity implements View.OnClickListener {

    Button btnCadastrar;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);

        variaveis();


    }

    private void variaveis(){
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnEntrar){
            login();
        }
        else if (v == btnCadastrar){
            register();
        }

    }

    private void login(){
        Intent entrar = new Intent(this, login.class);
        startActivity(entrar);

    }

    private void register(){

        Intent cadastrar = new Intent(this, register.class);
        startActivity(cadastrar);

    }
}
