package com.example.finance.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.R;
import com.example.finance.adapter.RelAdapter;
import com.example.finance.configDaos.LancamentoDao;
import com.example.finance.entidades.ControleUsuario;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.text.ParseException;
import java.util.List;

public class Principal extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ToggleButton tgHome;
    ToggleButton tgLancamento;
    ToggleButton tgReport;
    ToggleButton tgProfile;
    //Usuario usuario;
    ListView lsListaLc;
    LancamentoDao lancamentoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        try {
            variaveis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("Usuario", ControleUsuario.getUsuario().getLogin());
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

        List<Lancamento> lancamentos = lancamentoDao.listarLancUsuario(ControleUsuario.getUsuario().getId());
        ArrayAdapter adapter = new RelAdapter(this,lancamentos);
        lsListaLc.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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