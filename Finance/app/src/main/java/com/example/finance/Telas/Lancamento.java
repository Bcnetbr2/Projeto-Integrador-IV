package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.finance.R;
import com.example.finance.configDaos.CategoriaDao;
import com.example.finance.configDaos.FornecedorDao;
import com.example.finance.entidades.Categoria;
import com.example.finance.entidades.Fornecedor;

import java.util.List;

public class Lancamento extends Activity implements View.OnClickListener{

    EditText edtAddCategoria;
    EditText edtAddValorGasto;
    Button btnAdicionar;
    Spinner spTipos;
    Spinner spCategoria;
    Spinner spFornecedor;
    Button btnAdicionarMais;
    ToggleButton tgHome;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    TextView tvAdicionarCateg;
    TextView tvFornecedor;
    Button btnAdicionarLancamento;
    Categoria categoria;
    CategoriaDao categoriaDao;
    ArrayAdapter<Categoria> adapterCategoria;
    List<Categoria> listaCategoria;
    Fornecedor fornecedor;
    FornecedorDao fornecedorDao;
    ArrayAdapter<Fornecedor> adapterFornecedor;
    List<Fornecedor>listaFornecedor;
    String[] tipo = {"Receita","Despesa"};
    ArrayAdapter<String> adapterTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lancamento);
        variaveis();
        atualizarTipo();
        atualizarCategoria();
        atualizarFornecedor();






    }

    public void variaveis(){


        btnAdicionar = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionar.setOnClickListener(this);

        spTipos = (Spinner) findViewById(R.id.spTipos);
        spCategoria = (Spinner)findViewById(R.id.spCategoria);
        spFornecedor =(Spinner)findViewById(R.id.spFornecedor);
        //String[] lsPeso = getResources().getStringArray(R.array.tipo);


        tvAdicionarCateg= (TextView) findViewById(R.id.tvAdicionarCateg);
        tvAdicionarCateg.setOnClickListener(this);

        tvFornecedor = (TextView) findViewById(R.id.tvAdicionarFornecedor);
        tvFornecedor.setOnClickListener(this);

        btnAdicionarLancamento = (Button) findViewById(R.id.btnAdicionarFornecedor);
        btnAdicionarLancamento.setOnClickListener(this);

        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

        categoriaDao = new CategoriaDao(this);
        fornecedorDao = new FornecedorDao(this);


    }

    private void atualizarCategoria(){

        listaCategoria = categoriaDao.listar();
        adapterCategoria = new ArrayAdapter<Categoria>(Lancamento.this,android.R.layout.simple_list_item_1,listaCategoria);
        spCategoria.setAdapter(adapterCategoria);
    }

    private void atualizarFornecedor(){

        listaFornecedor = fornecedorDao.listar();
        adapterFornecedor = new ArrayAdapter<Fornecedor>(Lancamento.this,android.R.layout.simple_list_item_1,listaFornecedor);
        spFornecedor.setAdapter(adapterFornecedor);
    }

    private void atualizarTipo(){

        adapterTipo = new ArrayAdapter<String>(Lancamento.this,android.R.layout.simple_list_item_1,tipo);
        spTipos.setAdapter(adapterTipo);

    }


    @Override
    public void onClick(View v) {
        if (v == btnAdicionar){
        Toast.makeText(this, "Adicionou uma nova categoria", Toast.LENGTH_SHORT).show();
        }

        else if (v == tvAdicionarCateg){
            Intent telaAdicionar = new Intent(this, AdicionarCategorias.class);
            startActivity(telaAdicionar);
        }
         else if (v == tgReport){
            entrarReport();
        }
        else if (v == tgProfile){
            entrarProfile();
        }
        else if (v == tvFornecedor){
            entrarFornecedor();
        }
        else if (v == tgHome){
            entrarHome();
        }

        else if (v == btnAdicionarLancamento){
            adicionarLancamento();
        }
    }


    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
    private void entrarFornecedor(){
        Intent profile = new Intent(this, AdicionarFornecedor.class);
        startActivity(profile);
    }

    private void entrarHome(){
        Intent home = new Intent(this, Principal.class);
        startActivity(home);
    }
    private void adicionarLancamento(){
        Toast.makeText(this, "Lançamento realizado com sucesso", Toast.LENGTH_LONG).show();
    }

  }

