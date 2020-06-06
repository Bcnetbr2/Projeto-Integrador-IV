package com.example.finance.Telas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finance.Controle.ControleEntidades;
import com.example.finance.R;
import com.example.finance.configDaos.CategoriaDao;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Categoria;

import java.text.ParseException;
import java.util.List;

public class Filtro extends Activity implements View.OnClickListener{

    EditText edtDataInicial;
    EditText edtDataFinal;
    Spinner spCategoriaFiltro;
    Button btnAdicionarFiltro;
    ArrayAdapter<Categoria> adapterCategoriaFiltro;
    CategoriaDao categoriaDao;
    List<Categoria> categoriaLista;
    ConverterData converterData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro);
        variaveis();


    }

    private void variaveis(){

        edtDataInicial = (EditText) findViewById(R.id.edtDataInicialFiltro);
        edtDataFinal = (EditText) findViewById(R.id.edtDataFinalFiltro);
        spCategoriaFiltro = (Spinner) findViewById(R.id.spCategoriaFiltro);


        btnAdicionarFiltro = (Button) findViewById(R.id.btnAdicionarFiltro);
        btnAdicionarFiltro.setOnClickListener(this);

        categoriaDao = new CategoriaDao(this);
        atualizarCategoriaFiltro();
        converterData = new ConverterData();


    }

    private void atualizarCategoriaFiltro(){

        categoriaLista = categoriaDao.listar();
        adapterCategoriaFiltro = new ArrayAdapter<Categoria>(Filtro.this,android.R.layout.simple_list_item_1,categoriaLista);
        spCategoriaFiltro.setAdapter(adapterCategoriaFiltro);

    }

    private void adicionarFiltro() throws ParseException {

        ControleEntidades.setDataInicial(converterData.converterStringData(edtDataInicial.getText().toString()));
        Log.e("Data Inicial","Data: " + ControleEntidades.getDataInicial());
        ControleEntidades.setDataFinal(converterData.converterStringData(edtDataFinal.getText().toString()));
        Log.e("Data Final","Data: " + ControleEntidades.getDataFinal());
        Categoria cat = (Categoria)spCategoriaFiltro.getSelectedItem();
        ControleEntidades.setCategoria(cat);
        Log.e("Nome categoria","Nome: " + ControleEntidades.getCategoria().getDescricao());

        ControleEntidades.setStatusFiltro("ativo");


    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarFiltro){
            try {
                adicionarFiltro();

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Filtro adicionado com sucesso", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
