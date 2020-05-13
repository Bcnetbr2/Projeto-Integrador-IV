package com.example.finance.Telas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finance.R;
import com.example.finance.configDaos.FornecedorDao;
import com.example.finance.entidades.Fornecedor;

import java.util.List;

public class AdicionarFornecedor extends Activity implements View.OnClickListener{

    EditText edtNomeFornecedor;
    EditText edtEmailFornecedor;
    EditText edtTelefoneFornecedor;
    EditText edtUfFornecedor;

    Button btnAdicionarFornecedor;

    Fornecedor fornecedor;
    FornecedorDao fornecedorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fornecedor);

        variaveis();
        List<Fornecedor> lista = fornecedorDao.listar();

        for ( Fornecedor f:lista) {

            Log.e("fornecedor", f.getNome().toString());

        }


}

private void variaveis(){

    edtNomeFornecedor = (EditText) findViewById(R.id.edtNomeFornecedor);
    edtEmailFornecedor = (EditText) findViewById(R.id.edtEmailFornecedor);
    edtTelefoneFornecedor = (EditText) findViewById(R.id.edtTelefoneFornecedor);
    edtUfFornecedor = (EditText) findViewById(R.id.edtUfFornecedor);

    btnAdicionarFornecedor = (Button) findViewById(R.id.btnAdicionarFornecedor);
    btnAdicionarFornecedor.setOnClickListener(this);

    fornecedor = new Fornecedor();
    fornecedorDao = new FornecedorDao(this);



}

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarFornecedor){
            adicionarFornecedor();
        }
    }

    private void adicionarFornecedor(){

        fornecedor.setNome(edtNomeFornecedor.getText().toString());
        fornecedor.setTelefone(edtTelefoneFornecedor.getText().toString());
        fornecedor.setEmail(edtEmailFornecedor.getText().toString());
        fornecedor.setUf(edtUfFornecedor.getText().toString());

        long id = fornecedorDao.inserir(fornecedor);

        Toast.makeText(this, "AdicionarFornecedor adicionado com sucesso", Toast.LENGTH_LONG).show();
    }
}

