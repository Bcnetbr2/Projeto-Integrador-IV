package com.example.finance.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.R;
import com.example.finance.adapter.RelAdapter;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.Controle.ControleEntidades;
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
    //Usuario usuario;
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
        Log.e("Usuario", ControleEntidades.getUsuario().getLogin());
        //receberUsuario();


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
            finish();
        }
        else if (v == tgReport){
            entrarReport();
            finish();
            }
        else if (v == tgProfile){
            entrarProfile();
            finish();
        }
    }

    private void entrarLancamento(){
        Intent adicionarCategoria = new Intent(this, Tela_Lancamento.class);
        startActivity(adicionarCategoria);
        finish();
        //enviarUsuario();
    }

    private void entrarReport(){
        Intent report = new Intent(this, Report.class);
        startActivity(report);
       // enviarUsuario2();
    }

    private void entrarProfile(){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
        //enviarUsuarioPerfil();
    }
    private void atualizarLista() throws ParseException {

        Date dataFinal = new Date();
        Log.e("Usuario", "Data" + dataFinal);
        Date dataInicial = converterData.DataInicial(dataFinal);
        Log.e("Usuario", "Data" + dataInicial);
        Log.e("Usuario", "Data" + ControleEntidades.getUsuario().getId());
        List<Lancamento> lancamentos = lancamentoDao.listarLancUsuario(ControleEntidades.getUsuario().getId(),dataInicial,dataFinal);
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
        //Log.e("Lançamento",ControleEntidades.getLancamento().getUsuario().getLogin());
        //Log.e("Lançamento",ControleEntidades.getLancamento().getTipo());
        //Log.e("Lançamento",ControleEntidades.getLancamento().getCategoria().getDescricao());
        //Log.e("Lançamento",ControleEntidades.getLancamento().getFornecedor().getNome());
        //Log.e("Lançamento",String.valueOf(ControleEntidades.getLancamento().getData()));
        //Log.e("Lançamento",ControleEntidades.getLancamento().getUsuario().getLogin());

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        startActivity(telaLancamento);

    }

    /*private void receberUsuario(){

        if(getIntent().getExtras().getSerializable("usuario") != null){

            usuario = (Usuario)getIntent().getExtras().getSerializable("usuario");

        }
        else{
            Toast.makeText(this,"não recebido",Toast.LENGTH_LONG).show();
            Log.e("Usuario","não recebido");
        }

    }
    /*
     */
    /*private void enviarUsuario(){

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(telaLancamento);

    }

     */
    /*private void enviarUsuario2(){

        Intent telaLancamento = new Intent(this,Report.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(telaLancamento);

    }

     */
    /*private void enviarUsuarioPerfil(){

        Intent telaLancamento = new Intent(this,Profile.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(telaLancamento);

    }

     */
}