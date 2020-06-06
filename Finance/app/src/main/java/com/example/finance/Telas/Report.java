package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.finance.Controle.ControleEntidades;
import com.example.finance.R;
import com.example.finance.adapter.RelAdapter;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Report extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgProfile;
    Button btnFiltro;
    ListView lstListaResultado;
    Usuario usuario;
    LancamentoDao lancamentoDao;

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

        lancamentoDao = new LancamentoDao(this);
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
        Log.e("StatusFilltro",ControleEntidades.getStatusFiltro());
        if(ControleEntidades.getStatusFiltro().equals("ativo")){
            try {
                Log.e("Data Inicial","Data: " + ControleEntidades.getDataInicial());
                Log.e("Data Final","Data: " + ControleEntidades.getDataFinal());
                Log.e("Nome categoria","Nome: " + ControleEntidades.getCategoria().getDescricao());
                Log.e("StatusFilltro",ControleEntidades.getStatusFiltro());
                atualizarListaFiltro();

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
    }
    private void atualizarListaFiltro() throws ParseException {

        List<Lancamento> lancamentos = lancamentoDao.listarLancFiltro(ControleEntidades.getUsuario().getId(),ControleEntidades.getCategoria().getDescricao(),ControleEntidades.getDataInicial(),ControleEntidades.getDataFinal());
        ArrayAdapter adapter = new RelAdapter(this,lancamentos);
        lstListaResultado.setAdapter(adapter);
        ControleEntidades.setStatusFiltro("inativo");


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Lancamento lancamento = (Lancamento)lstListaResultado.getAdapter().getItem(position);
        ControleEntidades.setLancamento(lancamento);
        ControleEntidades.setStatus("ativo");

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        startActivity(telaLancamento);


    }

    /*
    private void receberUsuario(){

        if(getIntent().getExtras().getSerializable("usuario") != null){

            usuario = (Usuario)getIntent().getExtras().getSerializable("usuario");
            Log.e("Usuario",usuario.getLogin());

        }
        else{
            Toast.makeText(this,"não recebido",Toast.LENGTH_LONG).show();
            Log.e("Usuario","não recebido");
        }

    }
    private void enviarUsuario(){

        Intent telaLancamento = new Intent(this,Tela_Lancamento.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(telaLancamento);


    }

     */
}
