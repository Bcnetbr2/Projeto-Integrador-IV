package com.example.finance.configConexao;

public class ScriptBD {

    public static String getCreateTableCategoria(){

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS categoria ( ");
        sql.append("     id         INTEGER      PRIMARY KEY AUTOINCREMENT,");
        sql.append("     nome       VARCHAR (50));");


        return sql.toString();
    }
    public static String getCreateTableFornecedor(){

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE  IF NOT EXISTS  fornecedor( ");
        sql.append("     id             INTEGER      PRIMARY KEY AUTOINCREMENT,");
        sql.append("     nome           VARCHAR (50),");
        sql.append("     telefone       VARCHAR (20),");
        sql.append("     email          VARCHAR (50),");
        sql.append("     UF             VARCHAR (2));");

        return sql.toString();

    }
    public static String getCreateTableUsuario() {

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS usuario( ");
        sql.append("     id         INTEGER      PRIMARY KEY AUTOINCREMENT,");
        sql.append("     Login      VARCHAR (50),");
        sql.append("     senha      VARCHAR (8),");
        sql.append("     email      VARCHAR (50),");
        sql.append("     fone       VARCHAR (20),");
        sql.append("     renda      REAL);");

        return sql.toString();
    }
    public static String getCreateTableLancamento(){

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS lancamento ( ");
        sql.append("     id              INTEGER             PRIMARY KEY AUTOINCREMENT,");
        sql.append("     id_usuario      INTEGER,");
        sql.append("     id_categoria    INTEGER,");
        sql.append("     id_fornecedor   INETGER,");
        sql.append("     tipo            VARCHAR (20),");
        sql.append("     data            DATE,");
        sql.append("     valor           REAL,");
        sql.append("     descricao     VARCHAR (50),");
        sql.append("FOREIGN KEY(id_usuario) REFERENCES usuario(id)");
        sql.append("FOREIGN KEY(id_categoria) REFERENCES categoria(id)");
        sql.append("FOREIGN KEY(id_fornecedor) REFERENCES fornecedor(id));");

        return sql.toString();
    }

}
