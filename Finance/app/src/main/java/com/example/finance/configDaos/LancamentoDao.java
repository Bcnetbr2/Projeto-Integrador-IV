package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Lancamento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDao {

    private final String TABELA = "lancamento";
    private final String[] CAMPOS = {"id","id_usuario","id_categoria","id_fornecedor","tipo","data","valor","descricao"};
    private Conexao conexao;
    private SQLiteDatabase finance;
    ConverterData dt = new ConverterData();

    public LancamentoDao(Context context){

        conexao = new Conexao(context);
        finance = conexao.getWritableDatabase();

    }

    private ContentValues preencherValores(Lancamento lancamento){

        ContentValues values = new ContentValues();
        ConverterData converterData = new ConverterData();

        values.put("id_usuario", lancamento.getUsuario().getId());
        values.put("id_categoria", lancamento.getCategoria().getId());
        values.put("id_fornecedor", lancamento.getFornecedor().getId());
        values.put("tipo", lancamento.getTipo());
        values.put("data", dt.formataDataLong(lancamento.getData()));
        values.put("valor", lancamento.getValor());
        values.put("descricao", lancamento.getDescricao());

        return values;
    }

    public long inserir(Lancamento lancamento){

        ContentValues values = preencherValores(lancamento);
        return finance.insert(TABELA,null,values);

    }

    public long alterar(Lancamento lancamento){

        ContentValues values = preencherValores(lancamento);
        return finance.update(TABELA,values,"id = ?", new String[] {String.valueOf(lancamento.getId())});

    }

    public long excluir(Lancamento lancamento){

        return finance.delete(TABELA,"id = ?", new String[] {String.valueOf(lancamento.getId())});

    }

    public List<Lancamento> listar() throws ParseException {

        Cursor c = finance.query(TABELA, CAMPOS,null,null,null,null,null);

        List<Lancamento> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while(c.moveToNext()){
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.getUsuario().setId(c.getLong(1));
            lancamento.getCategoria().setId(c.getLong(2));
            lancamento.getFornecedor().setId(c.getLong(3));
            lancamento.setTipo(c.getString(4));
            lancamento.setData(dt.converterLongData(c.getLong(5)));
            lancamento.setValor(c.getFloat(6));
            lancamento.setDescricao(c.getString(7));

            lista.add(lancamento);
        }
        return lista;
    }

}
