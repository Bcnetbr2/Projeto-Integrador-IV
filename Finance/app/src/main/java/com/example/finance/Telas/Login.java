package com.example.finance.Telas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finance.R;
import com.example.finance.configDaos.UsuarioDao;
import com.example.finance.entidades.Usuario;

import java.util.List;

public class Login extends Activity implements View.OnClickListener {


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    TextView tvSemConta;
    EditText edtLogin;
    EditText edtSenha;
    Button btnFinalizarLogin;
    UsuarioDao usuarioDao;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        variaveis();
    }

    private void variaveis(){



        tvSemConta = (TextView) findViewById(R.id.tvSemConta);
        tvSemConta.setOnClickListener(this);

        edtLogin = (EditText) findViewById(R.id.edtRecebeLogin);

        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnFinalizarLogin = (Button) findViewById(R.id.btnFinalizarLogin);
        btnFinalizarLogin.setOnClickListener(this);

        usuarioDao = new UsuarioDao(this);
    }

    @Override
    public void onClick(View v) {

         if (v == tvSemConta){

             Usuario us = new Usuario();
             us.setId(0L);

            naoTemCadastro(us);


        }
        else if (v == btnFinalizarLogin){
            login();
        }

    }

    private void naoTemCadastro(Usuario obj){
        Intent naotemcadastro = new Intent(this, Register.class);
        Bundle extras = new Bundle();
        extras.putSerializable("us",obj);
        naotemcadastro.putExtras(extras);

        startActivity(naotemcadastro);
    }

    private void finalizarLogin(){
        Intent finalizarlogin = new Intent(this, Principal.class);
        Intent telaLancamento = new Intent(this, Lancamento.class);
        Bundle extras = new Bundle();
        extras.putSerializable("usuario",usuario);
        telaLancamento.putExtras(extras);
        startActivity(finalizarlogin);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
                onLoginFailed();
                return;
        }
        else{
                if(verifcarAcesso(edtLogin.getText().toString(),edtSenha.getText().toString()) == true){

                    finalizarLogin();


                }
                else{

                    onLoginFailed();

                }
            }

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_SIGNUP) {
                if (resultCode == RESULT_OK) {

                    // TODO: Implement successful signup logic here
                    // By default we just finish the Activity and log them in automatically
                    this.finish();
                }
            }
        }

        @Override
        public void onBackPressed() {
            // Disable going back to the MainActivity
            moveTaskToBack(true);
        }

        public void onLoginSuccess() {
            btnFinalizarLogin.setEnabled(true);
            //finish();
        }

        public void onLoginFailed() {
            Toast.makeText(getBaseContext(), "Falha no Login, confira os campos", Toast.LENGTH_LONG).show();

            btnFinalizarLogin.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;

            String login = edtLogin.getText().toString();
            String password = edtSenha.getText().toString();

            if (login.isEmpty()) {
                edtLogin.setError("Coloque um login válido");
                valid = false;
            } else {
                edtLogin.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() > 8) {
                edtSenha.setError("Use uma senha com no minimo 4 caracteres");
                valid = false;
            } else {
                edtSenha.setError(null);
            }

            return valid;
        }
        private boolean verifcarAcesso(String login,String senha){

            boolean resposta = false;

            List<Usuario> listaUsuario = usuarioDao.PesquisarUsuario(login,senha);
            Usuario usuarioResultado = new Usuario();
            for (Usuario u:listaUsuario) {

                if(login.equals(u.getLogin()) && senha.equals(u.getSenha())) {

                    usuario = u;
                    resposta = true;
                }

            }
            return resposta;

        }
}
