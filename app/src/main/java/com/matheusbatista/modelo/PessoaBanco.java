package com.matheusbatista.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

/**
 * Created by matheus.batista on 05/04/2017.
 */

public class PessoaBanco {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public PessoaBanco(Context context){
        banco = new CriaBanco(context);
    }

    public String inserePessoa(String nome, String cpf, String telefone){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOMEPESSOA, nome);
        valores.put(CriaBanco.CPFPESSOA, cpf);
        valores.put(CriaBanco.TELEFONEPESSOA, telefone);

        resultado = db.insert(CriaBanco.TABELAPESSOA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public ArrayList<Pessoa> carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.IDPESSOA, banco.NOMEPESSOA, banco.CPFPESSOA, banco.TELEFONEPESSOA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELAPESSOA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();

        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            listaPessoas.add(new Pessoa(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return listaPessoas;
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.IDPESSOA + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELAPESSOA,where,null);
        db.close();
    }

}
