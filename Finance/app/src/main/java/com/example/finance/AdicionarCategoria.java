package com.example.finance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarCategoria extends Activity implements View.OnClickListener{

    EditText edtAddCategoria;
    EditText edtAddValorGasto;
    Button btnAdicionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_categoria);
        variaveis();

    }

    public void variaveis(){
        edtAddCategoria = (EditText) findViewById(R.id.edtAddCategoria);
        edtAddValorGasto = (EditText) findViewById(R.id.edtAddValorGasto);

        btnAdicionar = (Button) findViewById(R.id.btnSalvarDados);
        btnAdicionar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnAdicionar){
        Toast.makeText(this, "Adicionou uma nova categoria", Toast.LENGTH_SHORT).show();
    }
  }
}
