package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.entidades.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {

    private final String TABELA = "fornecedor";
    private final String[] CAMPOS = {"id","nome","Telefone","email","UF"};
    private Conexao conexao;
    private SQLiteDatabase finance;

    public FornecedorDao(Context context){

        conexao = new Conexao(context);
        finance = conexao.getWritableDatabase();

    }

    private ContentValues preencherValores(Fornecedor fornecedor){

        ContentValues values = new ContentValues();

        values.put("nome", fornecedor.getNome());
        values.put("telefone", fornecedor.getTelefone());
        values.put("email", fornecedor.getEmail());
        values.put("UF", fornecedor.getUf());

        return values;
    }

    public long inserir(Fornecedor fornecedor){

        ContentValues values = preencherValores(fornecedor);
        return finance.insert(TABELA,null,values);

    }

    public long alterar(Fornecedor fornecedor){

        ContentValues values = preencherValores(fornecedor);
        return finance.update(TABELA,values,"id = ?", new String[] {String.valueOf(fornecedor.getId())});

    }

    public long excluir(Fornecedor fornecedor){

        return finance.delete(TABELA,"id = ?", new String[] {String.valueOf(fornecedor.getId())});

    }

    public List<Fornecedor> listar(){

        Cursor c = finance.query(TABELA, CAMPOS,null,null,null,null,null);

        List<Fornecedor> lista = new ArrayList<>();
        while(c.moveToNext()){
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(c.getLong(0));
            fornecedor.setNome(c.getString(1));
            fornecedor.setTelefone(c.getString(2));
            fornecedor.setEmail(c.getString(3));
            fornecedor.setUf(c.getString(4));
            lista.add(fornecedor);
        }
        return lista;
    }

}
