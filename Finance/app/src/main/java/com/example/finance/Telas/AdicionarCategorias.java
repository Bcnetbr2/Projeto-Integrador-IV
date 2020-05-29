package com.example.finance.Telas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finance.R;
import com.example.finance.configDaos.CategoriaDao;
import com.example.finance.entidades.Categoria;

import java.util.List;

public class AdicionarCategorias extends Activity implements View.OnClickListener {


    EditText edtDescricao;
    Button btnAdicionarCateg;
    Categoria categoria;
    CategoriaDao categoriaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionarcategorias);
        variaveis();

        List<Categoria> lista = categoriaDao.listar();
        for (Categoria c:lista) {

            Log.e("categoria",c.getDescricao().toString());

        }

    }

    private void variaveis(){
        edtDescricao = (EditText) findViewById(R.id.edtNomeFornecedor);

        btnAdicionarCateg = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionarCateg.setOnClickListener(this);

        categoriaDao = new CategoriaDao(this);





    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarCateg){
            adicionarCateg();
        }


    }

    private void adicionarCateg(){
        categoria = new Categoria();
        categoria.setDescricao(edtDescricao.getText().toString());
        long id = categoriaDao.inserir(categoria);

        Toast.makeText(this, "Foi adicionado a categoria com sucecsso", Toast.LENGTH_LONG).show();

        finish();
    }

}

