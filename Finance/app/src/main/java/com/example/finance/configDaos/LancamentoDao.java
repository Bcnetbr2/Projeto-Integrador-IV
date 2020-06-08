package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Categoria;
import com.example.finance.entidades.Fornecedor;
import com.example.finance.entidades.Lancamento;
import com.example.finance.entidades.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LancamentoDao {

    private final String TABELA = "lancamento";
    private final String[] CAMPOS = {"id","id_usuario","id_categoria","id_fornecedor","tipo","data","valor","descricao"};
    private final String TABELAUSUARIO = "usuario";
    private final String[] CAMPOSUSUARIO = {"id","login","senha","email","fone","renda"};
    private final String TABELACATEGORIA = "categoria";
    private final String[] CAMPOSCATEGORIA = {"id","nome"};
    private final String TABELAFORNECEDOR = "fornecedor";
    private final String[] CAMPOSFORNECEDOR = {"id","nome","Telefone","email","UF"};
    private Conexao conexao;
    private SQLiteDatabase finance;
    ConverterData dt = new ConverterData();
    private UsuarioDao usuarioDao;

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
    public List<Usuario> listarUsuario(long id){

        Cursor c = finance.query(TABELAUSUARIO, CAMPOSUSUARIO,"id=?",new String[]{String.valueOf(id)},null,null,null);
        List<Usuario>lista = new ArrayList<>();
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

    public List<Categoria> listarCategoria(long id){

        Cursor c = finance.query(TABELACATEGORIA, CAMPOSCATEGORIA,"id=?", new String[]{String.valueOf(id)},null,null,null);

        List<Categoria> lista = new ArrayList<>();
        while(c.moveToNext()){
            Categoria categoria = new Categoria();
            categoria.setId(c.getLong(0));
            categoria.setDescricao(c.getString(1));

            lista.add(categoria);
        }
        return lista;
    }

    public List<Fornecedor> listarFornecedor(long id){

        Cursor c = finance.query(TABELAFORNECEDOR, CAMPOSFORNECEDOR,"id=?",new String[]{String.valueOf(id)},null,null,null);

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

    public Usuario retornoUsuario(long id){
        Usuario us = new Usuario();

        for (Usuario u:listarUsuario(id)) {

            if(id == u.getId()){

                us = u;

            }

        }
        return us;

    }

    public Categoria retornoCategoria(long id){
        Categoria ca = new Categoria();

        for (Categoria c:listarCategoria(id)) {

            if(id == c.getId()){

                ca = c;

            }

        }
        return ca;

    }

    public Fornecedor retornoFornecedor(long id){
        Fornecedor fo = new Fornecedor();

        for (Fornecedor f:listarFornecedor(id)) {

            if(id == f.getId()){

                fo = f;

            }

        }
        return fo;

    }

    public List<Lancamento> listar(long id_usuario) throws ParseException {
        String sql = "Select L.id, L.tipo, L.data, L.valor, L.descricao, u.id, u.login, u.senha, u.email, u.fone, u.renda, c.id, c.nome , f.id, f.nome, f.telefone, f.email as email, UF from lancamento as L \n" +
                "INNER JOIN usuario as u ON L.id_usuario = u.id\n" +
                "INNER JOIN categoria as c ON L.id_categoria = c.id\n" +
                "INNER JOIN fornecedor as f ON L.id_fornecedor = f.id\n"+
                "WHERE u.id = ? ORDER BY L.data DESC";
        String[] valores = new String[] {String.valueOf(id_usuario)};
        Cursor c = finance.rawQuery(sql,valores);

        List<Lancamento> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while(c.moveToNext()){
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));
            lancamento.setData(dt.converterLongData(c.getLong(2)));
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
            lancamento.getUsuario().setId(c.getLong(5));
            lancamento.getUsuario().setLogin(c.getString(6));
            lancamento.getUsuario().setSenha(c.getString(7));
            lancamento.getUsuario().setEmail(c.getString(8));
            lancamento.getUsuario().setFone(c.getString(9));
            lancamento.getUsuario().setRenda(c.getFloat(10));
            lancamento.getCategoria().setId(c.getLong(11));
            lancamento.getCategoria().setDescricao(c.getString(12));
            lancamento.getFornecedor().setId(c.getLong(13));
            lancamento.getFornecedor().setNome(c.getString(14));
            lancamento.getFornecedor().setTelefone(c.getString(15));
            lancamento.getFornecedor().setEmail(c.getString(16));
            lancamento.getFornecedor().setUf(c.getString(17));


            lista.add(lancamento);
        }
        return lista;
    }
    public List<Lancamento> listarLancUsuario(long id_usuario,Date dataInc,Date dataFim) throws ParseException {

        String sql = "Select L.id, L.tipo, L.data, L.valor, L.descricao,u.id,u.login,u.senha,u.email,u.fone,u.renda,c.id,c.nome ,f.nome ,f.id,f.telefone,f.email ,f.UF from lancamento as L \n" +
                "INNER JOIN usuario as u ON L.id_usuario = u.id\n" +
                "INNER JOIN categoria as c ON L.id_categoria = c.id\n" +
                "INNER JOIN fornecedor as f ON L.id_fornecedor = f.id\n" +
                "WHERE u.id = ? AND L.data BETWEEN ? AND ? ORDER BY L.data DESC;";
        String[] valores = new String[] {String.valueOf(id_usuario),String.valueOf(dataInc.getTime()),String.valueOf(dataFim.getTime())};
        Cursor c = finance.rawQuery(sql, valores);

        List<Lancamento> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while (c.moveToNext()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));
            lancamento.setData(dt.converterLongData(c.getLong(2)));
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
            lancamento.getUsuario().setId(c.getLong(5));
            lancamento.getUsuario().setLogin(c.getString(6));
            lancamento.getUsuario().setSenha(c.getString(7));
            lancamento.getUsuario().setEmail(c.getString(8));
            lancamento.getUsuario().setFone(c.getString(9));
            lancamento.getUsuario().setRenda(c.getFloat(10));
            lancamento.getCategoria().setId(c.getLong(11));
            lancamento.getCategoria().setDescricao(c.getString(12));
            lancamento.getFornecedor().setId(c.getLong(13));
            lancamento.getFornecedor().setNome(c.getString(14));
            lancamento.getFornecedor().setTelefone(c.getString(15));
            lancamento.getFornecedor().setEmail(c.getString(16));
            lancamento.getFornecedor().setUf(c.getString(17));

            lista.add(lancamento);
        }
        return lista;
    }
    public List<Lancamento> listarLancFiltro(long id_usuario,String categoria,Date dataInc,Date dataFim) throws ParseException {

        String sql = "Select L.id, L.tipo, L.data, L.valor, L.descricao,u.id,u.login,u.senha,u.email,u.fone,u.renda,c.id,c.nome ,f.nome ,f.id,f.telefone,f.email ,f.UF from lancamento as L \n" +
                "INNER JOIN usuario as u ON L.id_usuario = u.id\n" +
                "INNER JOIN categoria as c ON L.id_categoria = c.id\n" +
                "INNER JOIN fornecedor as f ON L.id_fornecedor = f.id\n" +
                "WHERE u.id = ? AND c.nome = ? AND L.data BETWEEN ? AND ? ORDER BY L.data DESC";
        String[] valores = new String[] {String.valueOf(id_usuario),categoria,String.valueOf(dataInc.getTime()),String.valueOf(dataFim.getTime())};
        Cursor c = finance.rawQuery(sql, valores);

        List<Lancamento> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while (c.moveToNext()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));
            lancamento.setData(dt.converterLongData(c.getLong(2)));
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
            lancamento.getUsuario().setId(c.getLong(5));
            lancamento.getUsuario().setLogin(c.getString(6));
            lancamento.getUsuario().setSenha(c.getString(7));
            lancamento.getUsuario().setEmail(c.getString(8));
            lancamento.getUsuario().setFone(c.getString(9));
            lancamento.getUsuario().setRenda(c.getFloat(10));
            lancamento.getCategoria().setId(c.getLong(11));
            lancamento.getCategoria().setDescricao(c.getString(12));
            lancamento.getFornecedor().setId(c.getLong(13));
            lancamento.getFornecedor().setNome(c.getString(14));
            lancamento.getFornecedor().setTelefone(c.getString(15));
            lancamento.getFornecedor().setEmail(c.getString(16));
            lancamento.getFornecedor().setUf(c.getString(17));

            lista.add(lancamento);
        }
        return lista;
    }
/*
    public List<Lancamento> listarFiltro(Date dataInic,Date dataFinal,long categoria) throws ParseException {

        Cursor c = finance.query(TABELA, CAMPOS,"data>=? and data<=? and id_categoria = ?",new String[]{String.valueOf(dataInic.getTime()),String.valueOf(dataFinal.getTime()),String.valueOf(categoria)},null,null,null);

        List<Lancamento> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while(c.moveToNext()){
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setUsuario(retornoUsuario(c.getLong(1)));
            lancamento.setCategoria(retornoCategoria(c.getLong(2)));
            lancamento.setFornecedor(retornoFornecedor(c.getLong(3)));
            lancamento.setTipo(c.getString(4));
            lancamento.setData(dt.converterLongData(c.getLong(5)));
            lancamento.setValor(c.getFloat(6));
            lancamento.setDescricao(c.getString(7));

            lista.add(lancamento);
        }
        return lista;
    }


 */
}
