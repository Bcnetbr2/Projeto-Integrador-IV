package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.configDaos.UsuarioDao;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Categoria;
import com.example.finance.entidades.Fornecedor;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Tela_Lancamento extends Activity implements View.OnClickListener{
    //Componentes da tela
    EditText edtAddCategoria;
    EditText edtValorGasto;
    EditText edtDescLancamento;
    EditText edtData;
    Spinner spTipos;
    Spinner spCategoria;
    Spinner spFornecedor;
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
    ConverterData dc;
    UsuarioDao usuarioDao;
    Usuario usuario;
    LancamentoDao lancamentoDao;
    //Button btnAdicionar;

    //Button btnAdicionarMais;

    //Botões do menu
    ToggleButton tgHome;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    ToggleButton tgAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lancamento);
        variaveis();
        atualizarTipo();
        atualizarCategoria();
        atualizarFornecedor();
        receberUsuario();
        try {
            List<Lancamento>listaLac = lancamentoDao.listar();
            for (Lancamento l:listaLac) {

                Log.e("Lançamento","usuario: " + l.getUsuario().getLogin());
                Log.e("Lançamento","categoria: " + l.getCategoria().getDescricao());
                Log.e("Lançamento","fornecedor: " + l.getFornecedor().getNome());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void variaveis(){


        //btnAdicionar = (Button) findViewById(R.id.btnAdicionarFornecedor);
        //btnAdicionar.setOnClickListener(this);
        //String[] lsPeso = getResources().getStringArray(R.array.tipo);
        //Spinner tipos
        spTipos = (Spinner) findViewById(R.id.spTipos);
        //Spinner categorias
        spCategoria = (Spinner)findViewById(R.id.spCategoria);
        //Spinner fornecedor
        spFornecedor =(Spinner)findViewById(R.id.spFornecedor);
        //campo data
        edtData = (EditText) findViewById(R.id.edtData);
        dc = new ConverterData();
        Date data = new Date();
        edtData.setText(dc.formataDataString(data));
        edtValorGasto = (EditText) findViewById(R.id.edtValorGasto);
        edtDescLancamento = (EditText) findViewById(R.id.edtDescLancamento);

        //link adicionar categoria
        tvAdicionarCateg= (TextView) findViewById(R.id.tvAdicionarCateg);
        tvAdicionarCateg.setOnClickListener(this);
        //link adicionar fornecedor
        tvFornecedor = (TextView) findViewById(R.id.tvAdicionarFornecedor);
        tvFornecedor.setOnClickListener(this);
        //botão adicionar lançamento
        btnAdicionarLancamento = (Button) findViewById(R.id.btnFinalizarLancamento);
        btnAdicionarLancamento.setOnClickListener(this);
        //conversor de data

        //Classes dao
        categoriaDao = new CategoriaDao(this);
        fornecedorDao = new FornecedorDao(this);
        lancamentoDao = new LancamentoDao(this);


        //Botôes menu
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

        tgAdd = (ToggleButton) findViewById(R.id.tgAdd);
        tgAdd.setOnClickListener(this);

    }

    private void atualizarCategoria(){

        listaCategoria = categoriaDao.listar();
        adapterCategoria = new ArrayAdapter<Categoria>(Tela_Lancamento.this,android.R.layout.simple_list_item_1,listaCategoria);
        spCategoria.setAdapter(adapterCategoria);
    }

    private void atualizarFornecedor(){

        listaFornecedor = fornecedorDao.listar();
        adapterFornecedor = new ArrayAdapter<Fornecedor>(Tela_Lancamento.this,android.R.layout.simple_list_item_1,listaFornecedor);
        spFornecedor.setAdapter(adapterFornecedor);


    }

    private void atualizarTipo(){

        adapterTipo = new ArrayAdapter<String>(Tela_Lancamento.this,android.R.layout.simple_list_item_1,tipo);
        spTipos.setAdapter(adapterTipo);

    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarCategoria();
        atualizarFornecedor();
    }

    @Override
    public void onClick(View v) {
       // if (v == btnAdicionar){
        //Toast.makeText(this, "Adicionou uma nova categoria", Toast.LENGTH_SHORT).show();
       // }

       if (v == tvAdicionarCateg){
            Intent telaAdicionar = new Intent(this, AdicionarCategorias.class);
            startActivity(telaAdicionar);
       }
       else if (v == tvFornecedor){
           entrarFornecedor();
       }
       else if (v == btnAdicionarLancamento){
           try {
               adicionarLancamento();
           } catch (ParseException e) {
               e.printStackTrace();
           }
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
       else if (v == tgAdd){
           entrarLacamento();
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
    private void entrarLacamento(){
        Intent telaLanc = new Intent(this,Tela_Lancamento.class);
        startActivity(telaLanc);
    }
    private void adicionarLancamento() throws ParseException {

        categoria = (Categoria)spCategoria.getSelectedItem();
        fornecedor= (Fornecedor)spFornecedor.getSelectedItem();
        String tipo = String.valueOf(spTipos.getSelectedItem());
        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(usuario);
        lancamento .setTipo(tipo);
        lancamento.setCategoria(categoria);
        lancamento.setFornecedor(fornecedor);
        lancamento.setData(dc.converterStringData(edtData.getText().toString()));
        lancamento.setValor(Float.parseFloat(String.valueOf(edtValorGasto.getText())));
        lancamento.setDescricao(edtDescLancamento.getText().toString());

        long id = lancamentoDao.inserir(lancamento);

        Toast.makeText(this, "Lançamento realizado com sucesso", Toast.LENGTH_LONG).show();
    }
    private void receberUsuario(){

        if(getIntent().getExtras().getSerializable("usuario") != null){

            usuario = (Usuario)getIntent().getExtras().getSerializable("usuario");

        }
        else{
            Toast.makeText(this,"não recebido",Toast.LENGTH_LONG).show();
            Log.e("Usuario","não recebido");
        }

    }

  }

