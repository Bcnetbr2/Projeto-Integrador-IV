package com.example.finance.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.R;
import com.example.finance.adapter.RelAdapter;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.controle.ControleEntidades;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Lancamento;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Principal extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ToggleButton tgHome;
    ToggleButton tgLancamento;
    ToggleButton tgReport;
    ToggleButton tgProfile;

    ListView lsListaLc;
    LancamentoDao lancamentoDao;
    ConverterData converterData = new ConverterData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        try {
            variaveis();
        } catch (ParseException e) {
            e.printStackTrace();
        }




}

    private void variaveis() throws ParseException {
        tgHome = (ToggleButton) findViewById(R.id.tgHome);
        tgHome.setOnClickListener(this);

        tgLancamento = (ToggleButton) findViewById(R.id.tgLancamento);
        tgLancamento.setOnClickListener(this);

        tgReport = (ToggleButton) findViewById(R.id.tgReport);
        tgReport.setOnClickListener(this);

        tgProfile = (ToggleButton) findViewById(R.id.tgProfile);
        tgProfile.setOnClickListener(this);

        lsListaLc = (ListView) findViewById(R.id.lstListaLc);
        lsListaLc.setOnItemClickListener(this);

        lancamentoDao = new LancamentoDao(this);
        atualizarLista();

}

    @Override
    public void onClick(View v) {
        if (v == tgLancamento){
            entrarLancamento();

        }
        else if (v == tgReport){
            entrarReport();

            }
        else if (v == tgProfile){
            entrarProfile();

        }
    }

    private void entrarLancamento(){
        Intent adicionarCategoria = new Intent(this, Tela_Lancamento.class);
        startActivity(adicionarCategoria);


    }

    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);

    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);

    }
    private void atualizarLista() throws ParseException {

        Date dataFinal = new Date();
        Date dataFinalZ = converterData.gerarDataFinal2(converterData.formataDataString2(dataFinal));

        Date dataInicial = converterData.gerarDataInicial2(converterData.formataDataString2(dataFinalZ));

        List<Lancamento> lancamentos = lancamentoDao.listarLancUsuario(ControleEntidades.getUsuario().getId(),dataInicial,dataFinalZ);
        ArrayAdapter adapter = new RelAdapter(this,lancamentos);
        lsListaLc.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            atualizarLista();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Lancamento lancamento = (Lancamento)lsListaLc.getAdapter().getItem(position);
        ControleEntidades.setLancamento(lancamento);
        ControleEntidades.setStatus("ativo");

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        startActivity(telaLancamento);

    }

}