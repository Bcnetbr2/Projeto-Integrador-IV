package com.example.finance.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.finance.R;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.entidades.Usuario;

import java.text.ParseException;
import java.util.List;

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
        Intent entrar = new Intent(this, Login.class);
        startActivity(entrar);

    }

    private void register(){
        Intent naotemcadastro = new Intent(this, Register.class);
        Bundle extras = new Bundle();
        Usuario obj = new Usuario();
        extras.putSerializable("us",obj);
        naotemcadastro.putExtras(extras);

        startActivity(naotemcadastro);
    }
}
