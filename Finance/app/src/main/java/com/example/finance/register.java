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

public class register extends Activity implements View.OnClickListener {
    private static final String TAG = "SignupActivity";

    EditText edtNome;
    EditText edtEmail;
    EditText edtTelefone;
    EditText edtSenha;
    EditText edtRenda;
    TextView tvJaTemCadastro;
    Button btnFinalizarCadastro;
    EditText edtConfirmeSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        variaveis();

    }

    private void variaveis(){

        edtNome = (EditText) findViewById(R.id.edtRecebeNome);
        edtEmail = (EditText) findViewById(R.id.edtRecebeEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtRenda = (EditText) findViewById(R.id.edtRenda);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtConfirmeSenha = (EditText) findViewById(R.id.edtConfirmeSenha) ;

        tvJaTemCadastro = (TextView) findViewById(R.id.tvJaTemCadastro);
        tvJaTemCadastro.setOnClickListener(this);

        btnFinalizarCadastro = (Button) findViewById(R.id.btnFinalizarCadastro);
        btnFinalizarCadastro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvJaTemCadastro){
            jaTemCadastro();
        }

        else if ( v == btnFinalizarCadastro){
            signup();
        }

    }

    private void jaTemCadastro(){
        Intent jatemcadastro = new Intent(this, login.class);
        startActivity(jatemcadastro);
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        else{
            Intent finalizarcadastro = new Intent(this, principal.class);
            startActivity(finalizarcadastro);
        }

        btnFinalizarCadastro.setEnabled(false);


        String name = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String mobile = edtTelefone.getText().toString();
        String password = edtSenha.getText().toString();
        String reEnterPassword = edtConfirmeSenha.getText().toString();

        // TODO: Implement your own signup logic here.


    }


    public void onSignupSuccess() {
        btnFinalizarCadastro.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Verifique os campos", Toast.LENGTH_LONG).show();

        btnFinalizarCadastro.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String mobile = edtTelefone.getText().toString();
        String password = edtSenha.getText().toString();
        String reEnterPassword = edtConfirmeSenha.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            edtNome.setError("precisa ter no minimo 3 caracteres");
            valid = false;
        } else {
            edtNome.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Insira um e-mail valido");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=11) {
            edtTelefone.setError("Entre com um numero de telefone valido, com DDD");
            valid = false;
        } else {
            edtTelefone.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtSenha.setError("É necessario uma senha com no minimo 4 caracteres");
            valid = false;
        } else {
            edtSenha.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            edtConfirmeSenha.setError("Senhas não conferem");
            valid = false;
        } else {
            edtConfirmeSenha.setError(null);
        }

        return valid;
    }
}
