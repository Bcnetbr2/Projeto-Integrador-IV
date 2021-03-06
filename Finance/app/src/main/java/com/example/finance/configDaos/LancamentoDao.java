package com.example.finance.configDaos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finance.configConexao.Conexao;
import com.example.finance.consulta.ObjetoConsultaCategoria;
import com.example.finance.consulta.ObjetoConsultaFornecedor;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Lancamento;

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
    // preenchendo valores no banco com os dados cadastrados no lançamento
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
    // inserindo no banco os dados cadastrados em lançamento
    public long inserir(Lancamento lancamento){

        ContentValues values = preencherValores(lancamento);
        return finance.insert(TABELA,null,values);

    }
    // Alterar no banco os dados cadastrados em lançamento
    public long alterar(Lancamento lancamento){

        ContentValues values = preencherValores(lancamento);
        return finance.update(TABELA,values,"id = ?", new String[] {String.valueOf(lancamento.getId())});

    }
    // Excluir no banco os dados cadastrados em lançamento
    public long excluir(Lancamento lancamento){

        return finance.delete(TABELA,"id = ?", new String[] {String.valueOf(lancamento.getId())});

    }
    //listando na tela principal e no relatorio os dados cadastrados na pagina de lançamento por cada usuario
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

        // pegando cada dado por usuario, categoria e fornecedor, para que dois usuarios não vejam o que outro cadastrou
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
    // listar lançamentos por usuario
    public List<Lancamento> listarLancUsuario(long id_usuario,Date dataInc,Date dataFim) throws ParseException {

        String sql = "Select L.id, L.tipo, L.data, L.valor, L.descricao,u.id,u.login,u.senha,u.email,u.fone,u.renda,c.id,c.nome ,f.id, f.nome, f.telefone,f.email ,f.UF from lancamento as L \n" +
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
    //filtro para cada usuario
    public List<Lancamento> listarLancFiltro(long id_usuario,String categoria,Date dataInc,Date dataFim) throws ParseException {

        String sql = "Select L.id, L.tipo, L.data, L.valor, L.descricao,u.id,u.login,u.senha,u.email,u.fone,u.renda,c.id,c.nome ,f.id ,f.nome ,f.telefone,f.email ,f.UF from lancamento as L \n" +
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
    //Filtro por data por cada usuario
    public List<ObjetoConsultaMes> listarLancFiltroData(long id_usuario) throws ParseException {

        String sql = "Select strftime(\"%m-%Y\",data/1000,'unixepoch') AS mes,sum(valor),tipo as tipo  from lancamento  \n" +
                "WHERE id_usuario = ? GROUP BY mes,tipo ORDER BY tipo, mes DESC";
        String[] valores = new String[] {String.valueOf(id_usuario)};
        Cursor c = finance.rawQuery(sql, valores);

        List<ObjetoConsultaMes> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while (c.moveToNext()) {

            ObjetoConsultaMes resultado = new ObjetoConsultaMes();
            resultado.setMesAno(c.getString(0));
            resultado.setValorTotalGasto(c.getFloat(1));
            resultado.setTipo(c.getString(2));

            lista.add(resultado);
        }
        return lista;
    }
    //filtro de fornecedor por cada usuario
    public List<ObjetoConsultaFornecedor> listarLancFiltroDataFornecedor(long id_usuario) throws ParseException {

        String sql = "Select strftime(\"%m-%Y\",L.data/1000,'unixepoch') AS mes,sum(L.valor)AS total,f.nome from lancamento as L\n" +
                     "INNER JOIN fornecedor as f ON L.id_fornecedor = f.id \n" +
                     "WHERE L.id_usuario = ? GROUP BY f.nome,mes";
        String[] valores = new String[] {String.valueOf(id_usuario)};
        Cursor c = finance.rawQuery(sql, valores);

        List<ObjetoConsultaFornecedor> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while (c.moveToNext()) {

            ObjetoConsultaFornecedor resultado = new ObjetoConsultaFornecedor();
            resultado.setMes(c.getString(0));
            resultado.setValor(c.getFloat(1));
            resultado.setNomeFornecedor(c.getString(2));

            lista.add(resultado);
        }
        return lista;
    }
    //filtro por data e categoria por usuario
    public List<ObjetoConsultaCategoria> listarLancFiltroDataCategoria(long id_usuario) throws ParseException {

        String sql = "Select strftime(\"%m-%Y\",L.data/1000,'unixepoch') AS mes,sum(L.valor)AS total,c.nome from lancamento as L\n" +
                "INNER JOIN categoria as c ON L.id_categoria = c.id \n" +
                "WHERE L.id_usuario = ? GROUP BY c.nome,mes";
        String[] valores = new String[] {String.valueOf(id_usuario)};
        Cursor c = finance.rawQuery(sql, valores);

        List<ObjetoConsultaCategoria> lista = new ArrayList<>();
        ConverterData converterData = new ConverterData();

        while (c.moveToNext()) {

            ObjetoConsultaCategoria resultado = new ObjetoConsultaCategoria();
            resultado.setMes(c.getString(0));
            resultado.setValor(c.getFloat(1));
            resultado.setNomeCategoria(c.getString(2));

            lista.add(resultado);
        }
        return lista;
    }



}
