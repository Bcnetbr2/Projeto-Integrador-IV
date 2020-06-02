package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.finance.R;
import com.example.finance.entidades.Usuario;

public class Report extends Activity implements View.OnClickListener{

    ToggleButton tgHome;
    ToggleButton tgAdicionar;
    ToggleButton tgProfile;
    Button btnFiltro;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
        variaveis();
        //receberUsuario();

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
    }

    @Override
    public void onClick(View v) {
        if (v == tgAdicionar){
            entrarAdicionar();
            finish();
        }
        else if (v == tgProfile){
            entrarProfile();
            finish();
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
