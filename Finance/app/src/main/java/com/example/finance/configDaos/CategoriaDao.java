package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private final String TABELA = "categoria";
    private final String[] CAMPOS = {"id","nome"};
    private Conexao conexao;
    private SQLiteDatabase finance;

    public CategoriaDao(Context context){

        conexao = new Conexao(context);
        finance = conexao.getWritableDatabase();
    }
    // preenchendo valores na tabela Categoria, com as informações preenchidas no app
    private ContentValues preencherValores(Categoria categoria){

        ContentValues values = new ContentValues();

        values.put("nome", categoria.getDescricao());

        return values;
    }
    // Inserindo os dados cadastrado na tebela do banco
    public long inserir(Categoria categoria){

        ContentValues values = preencherValores(categoria);
        return finance.insert(TABELA,null,values);

    }
    // alterar dados da categoria cadastrada
    public long alterar(Categoria categoria){

        ContentValues values = preencherValores(categoria);
        return finance.update(TABELA,values,"id = ?", new String[] {String.valueOf(categoria.getId())});

    }
    // Excluindo dados da categoria cadastrada
    public long excluir(Categoria categoria){

        return finance.delete(TABELA,"id = ?", new String[] {String.valueOf(categoria.getId())});

    }
    // Carrega a lista no spinner com as categorias cadastradas no banco
    public List<Categoria> listar(){

        Cursor c = finance.query(TABELA, CAMPOS,null,null,null,null,null);

        List<Categoria> lista = new ArrayList<>();
        while(c.moveToNext()){
            Categoria categoria = new Categoria();
            categoria.setId(c.getLong(0));
            categoria.setDescricao(c.getString(1));

            lista.add(categoria);
        }
        return lista;
    }

}
