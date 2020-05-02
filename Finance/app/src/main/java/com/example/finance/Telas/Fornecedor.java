package com.example.finance.Telas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finance.R;

public class Fornecedor extends Activity implements View.OnClickListener{

    EditText edtNomeFornecedor;
    EditText edtEmailFornecedor;
    EditText edtTelefoneFornecedor;
    EditText edtUfFornecedor;
    Button btnAdicionarFornecedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fornecedor);
        variaveis();
}

private void variaveis(){

    edtNomeFornecedor = (EditText) findViewById(R.id.edtNomeFornecedor);
    edtEmailFornecedor = (EditText) findViewById(R.id.edtEmailFornecedor);
    edtTelefoneFornecedor = (EditText) findViewById(R.id.edtTelefoneFornecedor);
    edtUfFornecedor = (EditText) findViewById(R.id.edtUfFornecedor);

    btnAdicionarFornecedor = (Button) findViewById(R.id.btnAdicionarFornecedor);
    btnAdicionarFornecedor.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarFornecedor){
            adicionarFornecedor();
        }
    }

    private void adicionarFornecedor(){
        Toast.makeText(this, "Fornecedor adicionado com sucesso", Toast.LENGTH_LONG).show();
    }
}

