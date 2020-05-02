package com.example.finance.configConexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String NAME = "finance.db";
    private static final int VERSION = 1;

    /*private static final String SQL_CREATE = "create table categoria ( " +
            "id long primary key autoincrement, " +
            "descricacao  varchar(50));";

     */
    public Conexao(@Nullable Context context){super(context,NAME,null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptBD.getCreateTableCategoria());
        db.execSQL(ScriptBD.getCreateTableFornecedor());
        db.execSQL(ScriptBD.getCreateTableUsuario());
        db.execSQL(ScriptBD.getCreateTableLancamento());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


}
