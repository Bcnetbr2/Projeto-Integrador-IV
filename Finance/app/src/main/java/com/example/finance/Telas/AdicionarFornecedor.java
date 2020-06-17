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
import com.example.finance.controle.ControleEntidades;
import com.example.finance.entidades.Fornecedor;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

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
       if(ControleEntidades.getStatusForn().equals("consulta")){

           edtNomeFornecedor.setText(ControleEntidades.getFornecedor().getNome());
           edtEmailFornecedor.setText(ControleEntidades.getFornecedor().getEmail());
           edtTelefoneFornecedor.setText(ControleEntidades.getFornecedor().getTelefone());
           edtUfFornecedor.setText(ControleEntidades.getFornecedor().getUf());
           btnAdicionarFornecedor.setText("FECHAR");

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

    SimpleMaskFormatter FormatarFone = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
    MaskTextWatcher MascaraFone = new MaskTextWatcher(edtTelefoneFornecedor, FormatarFone);
    edtTelefoneFornecedor.addTextChangedListener(MascaraFone);

    SimpleMaskFormatter FormatarUF = new SimpleMaskFormatter("LL");
    MaskTextWatcher MascaraUF = new MaskTextWatcher(edtUfFornecedor, FormatarUF);
    edtUfFornecedor.addTextChangedListener(MascaraUF);

}

    @Override
    public void onClick(View v) {
        if (v == btnAdicionarFornecedor){
            if(btnAdicionarFornecedor.getText().equals("FECHAR")){
                finish();
                ControleEntidades.setStatusForn("");
            }
            else{
                adicionarFornecedor();
            }

        }

    }

    private void adicionarFornecedor(){

        fornecedor.setNome(edtNomeFornecedor.getText().toString());
        fornecedor.setTelefone(edtTelefoneFornecedor.getText().toString());
        fornecedor.setEmail(edtEmailFornecedor.getText().toString());
        fornecedor.setUf(edtUfFornecedor.getText().toString());

        long id = fornecedorDao.inserir(fornecedor);

        Toast.makeText(this, "Fornecedor adicionado com sucesso", Toast.LENGTH_LONG).show();

        finish();
    }
}

