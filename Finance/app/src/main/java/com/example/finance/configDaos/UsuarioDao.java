package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final String TABELA = "usuario";
    private final String[] CAMPOS = {"id","Login","senha","email","fone","renda"};
    private Conexao conexao;
    private SQLiteDatabase finance;

    public UsuarioDao(Context context){

        conexao = new Conexao(context);
        finance = conexao.getWritableDatabase();

    }

    private ContentValues preencherValores(Usuario usuario){

        ContentValues values = new ContentValues();

        values.put("Login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("email", usuario.getEmail());
        values.put("fone", usuario.getFone());
        values.put("renda", usuario.getRenda());

        return values;
    }

    public long inserir(Usuario usuario){

        ContentValues values = preencherValores(usuario);
        return finance.insert(TABELA,null,values);

    }

    public long alterar(Usuario usuario){

        ContentValues values = preencherValores(usuario);
        return finance.update(TABELA,values,"id = ?", new String[] {String.valueOf(usuario.getId())});

    }

    public long excluir(Usuario usuario){

        return finance.delete(TABELA,"id = ?", new String[] {String.valueOf(usuario.getId())});

    }

    public List<Usuario> listar(){

        Cursor c = finance.query(TABELA, CAMPOS,null,null,null,null,null);

        List<Usuario> lista = new ArrayList<>();
        while(c.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(0));
            usuario.setLogin(c.getString(1));
            usuario.setSenha(c.getString(2));
            usuario.setEmail(c.getString(3));
            usuario.setFone(c.getString(4));
            usuario.setRenda(c.getFloat(5));
            lista.add(usuario);
        }
        return lista;
    }

}
