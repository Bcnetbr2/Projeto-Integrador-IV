package com.example.finance.Telas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finance.controle.ControleEntidades;
import com.example.finance.R;
import com.example.finance.configDaos.CategoriaDao;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Categoria;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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

        SimpleMaskFormatter FormatarDataInicial = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher MascaraDataInicial = new MaskTextWatcher(edtDataInicial, FormatarDataInicial);
        edtDataInicial.addTextChangedListener(MascaraDataInicial);

        SimpleMaskFormatter FormatarDataFinal = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher MascaraDataFinal = new MaskTextWatcher(edtDataFinal, FormatarDataFinal);
        edtDataFinal.addTextChangedListener(MascaraDataFinal);


    }

    private void atualizarCategoriaFiltro(){

        categoriaLista = categoriaDao.listar();
        adapterCategoriaFiltro = new ArrayAdapter<Categoria>(Filtro.this,android.R.layout.simple_list_item_1,categoriaLista);
        spCategoriaFiltro.setAdapter(adapterCategoriaFiltro);

    }

    private void adicionarFiltro() throws ParseException {

        ControleEntidades.setDataInicial(converterData.gerarDataInicial(edtDataInicial.getText().toString()));

        ControleEntidades.setDataFinal(converterData.gerarDataFinal(edtDataFinal.getText().toString()));

        Categoria cat = (Categoria)spCategoriaFiltro.getSelectedItem();
        ControleEntidades.setCategoria(cat);


        ControleEntidades.setStatusFiltro("ativo");


    }

    @Override
    public void onClick(View v) {
        if(Validador()) {
            if (v == btnAdicionarFiltro) {
                try {
                    adicionarFiltro();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Filtro adicionado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else{

            Toast.makeText(this, "O filtro não pode ser adicionado!", Toast.LENGTH_LONG).show();

        }

    }
    public boolean Validador(){

        boolean validador = true;

        String dataInicial = edtDataInicial.getText().toString();
        String dataFinal = edtDataFinal.getText().toString();

        if(dataInicial.isEmpty()){

            edtDataInicial.setError("Preencha o campo!");
            validador = false;

        }
        else{

            edtDataInicial.setError(null);

        }
        if(dataFinal.isEmpty()){

            edtDataFinal.setError("Preencha o campo");
            validador = false;

        }
        return validador;

    }
}
