package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.finance.controle.ControleEntidades;
import com.example.finance.R;
import com.example.finance.adapter.CatAdapter;
import com.example.finance.adapter.FornAdapter;
import com.example.finance.adapter.MesAdapter;
import com.example.finance.adapter.RelAdapter;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.consulta.ObjetoConsultaCategoria;
import com.example.finance.consulta.ObjetoConsultaFornecedor;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.text.ParseException;
import java.util.List;

public class Report extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgProfile;
    Button btnFiltro;
    CheckBox ckbMensal;
    CheckBox ckbFiltroCat;
    CheckBox ckbFitroForn;
    TextView txtCampoNome;
    TextView txtCampoMes;
    TextView txtCampoValor;

    ListView lstListaResultado;
    Usuario usuario;
    LancamentoDao lancamentoDao;
    TextView txtFiltro;
    ConverterData converterData = new ConverterData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
        variaveis();
        //receberUsuario();
        try {
            atualizarLista();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void variaveis(){
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgAdicionar = (ToggleButton) findViewById(R.id.tgAdd);
        tgAdicionar.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

        btnFiltro = (Button) findViewById(R.id.btnFiltro);
        btnFiltro.setOnClickListener(this);

        lstListaResultado = (ListView)findViewById(R.id.lstResultadoLista);
        lstListaResultado.setOnItemClickListener(this);

        ckbMensal = (CheckBox) findViewById(R.id.ckbMensal);
        ckbMensal.setOnClickListener(this);

        ckbFiltroCat = (CheckBox) findViewById(R.id.ckbFitroCat);
        ckbFiltroCat.setOnClickListener(this);

        ckbFitroForn = (CheckBox) findViewById(R.id.ckbFiltroForn);
        ckbFitroForn.setOnClickListener(this);

        lancamentoDao = new LancamentoDao(this);

        txtFiltro = (TextView) findViewById(R.id.txtFiltro);

        txtCampoNome = (TextView) findViewById(R.id.txtCampoNome);
        txtCampoMes = (TextView) findViewById(R.id.txtCampoMes);
        txtCampoValor = (TextView) findViewById(R.id.txtCampoValor);


    }

    @Override
    public void onClick(View v) {
        if (v == tgAdicionar){
            entrarAdicionar();

        }
        else if (v == tgProfile){
            entrarProfile();

        }
        else if (v == tgHome){
            entrarHome();
            finish();

        }
        else if (v == btnFiltro){
            Intent filtro = new Intent(this, Filtro.class);
            startActivity(filtro);
        }
        else if(v == ckbMensal){

            if(ckbMensal.isChecked()){

                btnFiltro.setEnabled(false);
                ckbFiltroCat.setEnabled(false);
                ckbFitroForn.setEnabled(false);
                txtFiltro.setText("");
                try {
                    balançoMensal();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            else if(!ckbMensal.isChecked()){

                btnFiltro.setEnabled(true);
                ckbFiltroCat.setEnabled(true);
                ckbFitroForn.setEnabled(true);

                try {
                    atualizarLista();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }
        else if(v == ckbFiltroCat){

            if(ckbFiltroCat.isChecked()){
                btnFiltro.setEnabled(false);
                ckbMensal.setEnabled(false);
                ckbFitroForn.setEnabled(false);
                txtFiltro.setText("");
                try {
                    filtroPorCat();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            else if(!ckbFiltroCat.isChecked()){

                btnFiltro.setEnabled(true);
                ckbMensal.setEnabled(true);
                ckbFitroForn.setEnabled(true);
                try {
                    atualizarLista();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }
        else if(v == ckbFitroForn){

            if(ckbFitroForn.isChecked()){

                btnFiltro.setEnabled(false);
                ckbMensal.setEnabled(false);
                ckbFiltroCat.setEnabled(false);
                txtFiltro.setText("");
                try {
                    filtroPorForn();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(!ckbFitroForn.isChecked()){

                btnFiltro.setEnabled(true);
                ckbMensal.setEnabled(true);
                ckbFiltroCat.setEnabled(true);
                try {
                    atualizarLista();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private void entrarAdicionar(){

        Intent lancamento = new Intent(this, Tela_Lancamento.class);
        startActivity(lancamento);

        //enviarUsuario();

    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);

    }
    private void entrarHome(){
        Intent home = new Intent(this, Principal.class);
        startActivity(home);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(ControleEntidades.getStatusFiltro().equals("ativo")){
            try {

                atualizarListaFiltro();
                txtFiltro.setText("Periodo pesquisado: " + converterData.formataDataString2(ControleEntidades.getDataInicial()) + " até "+ converterData.formataDataString2(ControleEntidades.getDataFinal()));

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        else{
            try {
                atualizarLista();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    private void atualizarLista() throws ParseException {

        List<Lancamento> lancamentos = lancamentoDao.listar(ControleEntidades.getUsuario().getId());
        ArrayAdapter adapter = new RelAdapter(this,lancamentos);
        lstListaResultado.setAdapter(adapter);
        txtCampoNome.setText("Fornecedor");
        txtCampoMes.setText("Mes/Ano");
        txtCampoValor.setText("Valor");



    }
    private void atualizarListaFiltro() throws ParseException {

       List<Lancamento> lancamentos = lancamentoDao.listarLancFiltro(ControleEntidades.getUsuario().getId(),ControleEntidades.getCategoria().getDescricao(),ControleEntidades.getDataInicial(),ControleEntidades.getDataFinal());
        ArrayAdapter adapter = new RelAdapter(this,lancamentos);
        lstListaResultado.setAdapter(adapter);
        ControleEntidades.setStatusFiltro("inativo");
        txtCampoNome.setText("Fornecedor");
        txtCampoMes.setText("Mes/Ano");
        txtCampoValor.setText("Valor");


    }

    private void balançoMensal() throws ParseException {



        List<ObjetoConsultaMes> resultado = lancamentoDao.listarLancFiltroData(ControleEntidades.getUsuario().getId());
        ArrayAdapter adapter = new MesAdapter(this,resultado);
        lstListaResultado.setAdapter(adapter);
        txtCampoNome.setText("Tipo");
        txtCampoMes.setText("Mes/Ano");
        txtCampoValor.setText("Valor");

    }

    private void filtroPorCat() throws ParseException {



        List<ObjetoConsultaCategoria> resultado = lancamentoDao.listarLancFiltroDataCategoria(ControleEntidades.getUsuario().getId());
        ArrayAdapter adapter = new CatAdapter(this,resultado);
        lstListaResultado.setAdapter(adapter);
        txtCampoNome.setText("Categoria");
        txtCampoMes.setText("Mes/Ano");
        txtCampoValor.setText("Valor");

    }

    private void filtroPorForn() throws ParseException {



        List<ObjetoConsultaFornecedor> resultado = lancamentoDao.listarLancFiltroDataFornecedor(ControleEntidades.getUsuario().getId());
        ArrayAdapter adapter = new FornAdapter(this,resultado);
        lstListaResultado.setAdapter(adapter);
        txtCampoNome.setText("Fornecedor");
        txtCampoMes.setText("Mes/Ano");
        txtCampoValor.setText("Valor");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(!ckbMensal.isChecked() && !ckbFiltroCat.isChecked() && !ckbFitroForn.isChecked()) {
            Lancamento lancamento = (Lancamento) lstListaResultado.getAdapter().getItem(position);
            ControleEntidades.setLancamento(lancamento);
            ControleEntidades.setStatus("ativo");

            Intent telaLancamento = new Intent(this, Tela_Lancamento.class);
            startActivity(telaLancamento);
        }


    }


}
