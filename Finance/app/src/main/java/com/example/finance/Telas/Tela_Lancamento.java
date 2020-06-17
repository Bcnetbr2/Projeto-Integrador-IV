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
import com.example.finance.controle.ControleEntidades;
import com.example.finance.entidades.Fornecedor;
import com.example.finance.entidades.Lancamento;

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
    Button btnExcluirLancamento;
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
    Button btnMostrarFornecedor;
    UsuarioDao usuarioDao;

    LancamentoDao lancamentoDao;

    ToggleButton tgHome;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    ToggleButton tgAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lancamento);
        variaveis();

        if(ControleEntidades.getStatus().equals("vazio")) {
            atualizarTipo();
            atualizarCategoria();
            atualizarFornecedor();
        }
        else{
            atualizarCategoria2();
            atualizarFornecedor2();
            preecherValores();
        }

    }

    public void variaveis(){

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
        //botão excluir lançamento
        btnExcluirLancamento = (Button) findViewById(R.id.btnExcluirLancamento);
        btnExcluirLancamento.setOnClickListener(this);
        btnMostrarFornecedor = (Button) findViewById(R.id.btnMostrarFornecedor);
        btnMostrarFornecedor.setOnClickListener(this);
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

        edtData.setEnabled(false);

        if(ControleEntidades.getStatus().equals("vazio")){
            btnExcluirLancamento.setVisibility(View.INVISIBLE);
            btnExcluirLancamento.setEnabled(false);
            Log.e("Status",ControleEntidades.getStatus());
        }
        else if(ControleEntidades.getStatus().equals("ativo")){
            btnExcluirLancamento.setEnabled(true);
            Log.e("Status",ControleEntidades.getStatus());
        }

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
       else if(v == btnExcluirLancamento){
           try {
               excluirLancamento();
           } catch (ParseException e) {
               e.printStackTrace();
           }
       }
       else if (v == tgReport){
            entrarReport();
           ControleEntidades.setStatus("vazio");
            finish();
       }
       else if (v == tgProfile){
            entrarProfile();
            ControleEntidades.setStatus("vazio");
            finish();
       }
       else if (v == tgHome){
            entrarHome();
           ControleEntidades.setStatus("vazio");
            finish();

       }
       else if (v == tgAdd){
           entrarLacamento();
           ControleEntidades.setStatus("vazio");
           finish();
       }
       else if(v==btnMostrarFornecedor){

           MostrarFornecedor();

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
        lancamento.setUsuario(ControleEntidades.getUsuario());
        lancamento.setTipo(tipo);
        lancamento.setCategoria(categoria);
        lancamento.setFornecedor(fornecedor);
        lancamento.setData(dc.converterStringData(edtData.getText().toString()));
        lancamento.setValor(Float.parseFloat(String.valueOf(edtValorGasto.getText())));
        lancamento.setDescricao(edtDescLancamento.getText().toString());

        Log.e("Data","Data " + lancamento.getData());

        if(ControleEntidades.getStatus().equals("vazio")) {

            long id = lancamentoDao.inserir(lancamento);

            Toast.makeText(this, "Lançamento realizado com sucesso", Toast.LENGTH_LONG).show();
            finish();
        }
        else if(ControleEntidades.getStatus().equals("ativo")){

            lancamento.setId(ControleEntidades.getLancamento().getId());

            long id = lancamentoDao.alterar(lancamento);

            Toast.makeText(this, "Lançamento atualizado com sucesso", Toast.LENGTH_LONG).show();
            ControleEntidades.setStatus("vazio");
            finish();

        }
        finish();
    }
    private void excluirLancamento() throws ParseException {

        categoria = (Categoria)spCategoria.getSelectedItem();
        fornecedor= (Fornecedor)spFornecedor.getSelectedItem();
        String tipo = String.valueOf(spTipos.getSelectedItem());
        Lancamento lancamento = new Lancamento();
        lancamento.setId(ControleEntidades.getLancamento().getId());
        lancamento.setUsuario(ControleEntidades.getUsuario());
        lancamento.setTipo(tipo);
        lancamento.setCategoria(categoria);
        lancamento.setFornecedor(fornecedor);
        lancamento.setData(dc.converterStringData(edtData.getText().toString()));
        lancamento.setValor(Float.parseFloat(String.valueOf(edtValorGasto.getText())));
        lancamento.setDescricao(edtDescLancamento.getText().toString());



        if(ControleEntidades.getStatus().equals("vazio")) {

            Toast.makeText(this, "Não foi possivel excluir o lançamento!", Toast.LENGTH_LONG).show();
            finish();
        }
        else if(ControleEntidades.getStatus().equals("ativo")){

            long id = lancamentoDao.excluir(lancamento);

            Toast.makeText(this, "Lançamento excluido com sucesso!", Toast.LENGTH_LONG).show();
            ControleEntidades.setStatus("vazio");
            finish();
        }
    }
    private void MostrarFornecedor(){

        Intent profile = new Intent(this, AdicionarFornecedor.class);
        startActivity(profile);
        Fornecedor fornecedor = (Fornecedor)spFornecedor.getSelectedItem();
        ControleEntidades.setFornecedor(fornecedor);
        ControleEntidades.setStatusForn("consulta");

    }
    private void atualizarCategoria2() {

        listaCategoria = categoriaDao.listar();
        adapterCategoria = new ArrayAdapter<Categoria>(Tela_Lancamento.this, android.R.layout.simple_list_item_1, listaCategoria);
        spCategoria.setAdapter(adapterCategoria);

        for (int i = 0; i < listaCategoria.size(); i++) {
            if (listaCategoria.get(i).getDescricao().toString().equals(ControleEntidades.getLancamento().getCategoria().getDescricao().toString())) {
                spCategoria.setSelection(i);
            }
        }
    }

    private void atualizarFornecedor2(){

        listaFornecedor = fornecedorDao.listar();
        adapterFornecedor = new ArrayAdapter<Fornecedor>(Tela_Lancamento.this,android.R.layout.simple_list_item_1,listaFornecedor);
        spFornecedor.setAdapter(adapterFornecedor);
        int p = 3;

        for(int i = 0;i<listaFornecedor.size();i++){
            if(listaFornecedor.get(i).getNome().toString().equals(ControleEntidades.getLancamento().getFornecedor().getNome().toString())){
                spFornecedor.setSelection(i);
            }
        }


    }

    private void setarTipo(){

        int posicao = 0;
        for(int i = 0; i<tipo.length;i++){

            if(tipo[i].toString().equals(ControleEntidades.getLancamento().getTipo().toString())){

                posicao = i;
                break;

            }

        }
        adapterTipo = new ArrayAdapter<String>(Tela_Lancamento.this,android.R.layout.simple_list_item_1,tipo);
        spTipos.setAdapter(adapterTipo);
        spTipos.setSelection(posicao);

    }
    private void preecherValores(){

        setarTipo();

        edtData.setText(String.valueOf(dc.formataDataString(ControleEntidades.getLancamento().getData())));
        edtValorGasto.setText(String.valueOf(ControleEntidades.getLancamento().getValor()));
        edtDescLancamento.setText(ControleEntidades.getLancamento().getDescricao());

    }

 }



