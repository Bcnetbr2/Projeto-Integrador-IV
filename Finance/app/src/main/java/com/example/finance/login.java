package com.example.finance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class login extends Activity implements View.OnClickListener {


    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    TextView tvEsqueciSenha;
    TextView tvSemConta;
    EditText edtEmail;
    EditText edtSenha;
    Button btnFinalizarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        variaveis();
    }

    private void variaveis(){

        tvEsqueciSenha = (TextView) findViewById(R.id.tvEsqueciSenha);
        tvEsqueciSenha.setOnClickListener(this);

        tvSemConta = (TextView) findViewById(R.id.tvSemConta);
        tvSemConta.setOnClickListener(this);

        edtEmail = (EditText) findViewById(R.id.edtRecebeEmail);

        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnFinalizarLogin = (Button) findViewById(R.id.btnSalvarDados);
        btnFinalizarLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvEsqueciSenha){
            esqueciSenha();
        }
        else if (v == tvSemConta){
            naoTemCadastro();
        }
        else if (v == btnFinalizarLogin){
            login();
        }

    }

    private void esqueciSenha(){
        Intent esquecisenha = new Intent(this, EsqueciSenha.class);
        startActivity(esquecisenha);
    }

    private void naoTemCadastro(){
        Intent naotemcadastro = new Intent(this, register.class);
        startActivity(naotemcadastro);
    }

    private void finalizarLogin(){
        Intent finalizarlogin = new Intent(this, principal.class);
        startActivityForResult(finalizarlogin, REQUEST_SIGNUP);
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
                Intent finalizarlogin = new Intent(this, principal.class);
                startActivityForResult(finalizarlogin, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
            finish();
        }

        public void onLoginFailed() {
            Toast.makeText(getBaseContext(), "Falha no Login, confira os campos", Toast.LENGTH_LONG).show();

            btnFinalizarLogin.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;

            String email = edtEmail.getText().toString();
            String password = edtSenha.getText().toString();

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Coloque um e-mail valido");
                valid = false;
            } else {
                edtEmail.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                edtSenha.setError("Use uma senha com no minimo 4 caracteres");
                valid = false;
            } else {
                edtSenha.setError(null);
            }

            return valid;
        }
}
