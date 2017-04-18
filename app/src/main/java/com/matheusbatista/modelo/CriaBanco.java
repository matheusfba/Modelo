package com.matheusbatista.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matheus.batista on 05/04/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    public static final String TABELAPESSOA = "Pessoa";
    public static final String IDPESSOA = "_id";
    public static final String NOMEPESSOA = "nome";
    public static final String CPFPESSOA = "cpf";
    public static final String TELEFONEPESSOA = "telefone";
    public static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELAPESSOA+"("
                + IDPESSOA + " integer primary key autoincrement, "
                + NOMEPESSOA + " text, "
                + CPFPESSOA + " text, "
                + TELEFONEPESSOA + " text "
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
