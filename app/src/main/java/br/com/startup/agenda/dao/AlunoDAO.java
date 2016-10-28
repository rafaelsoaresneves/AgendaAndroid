package br.com.startup.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.startup.agenda.modelo.Aluno;

/**
 * Created by rafael on 27/10/16.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE ALUNOS (ID INTEGER PRIMARY KEY, NOME TEXT NOT NULL, ENDERECO TEXT, TELEFONE TEXT, SITE TEXT, NOTA REAL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS ALUNOS";
        db.execSQL(sql);
        
        onCreate(db);
    }

    public void salvar(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("NOME", aluno.getNome());
        dados.put("ENDERECO", aluno.getEndereco());
        dados.put("TELEFONE", aluno.getTelefone());
        dados.put("SITE", aluno.getSite());
        dados.put("NOTA", aluno.getNota());

        db.insert("ALUNOS", null, dados);
    }

    public List<Aluno> buscarAlunos() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM ALUNOS;";
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();

        Aluno aluno;
        while (c.moveToNext()) {
            aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("ID")));
            aluno.setNome(c.getString(c.getColumnIndex("NOME")));
            aluno.setEndereco(c.getString(c.getColumnIndex("ENDERECO")));
            aluno.setTelefone(c.getString(c.getColumnIndex("TELEFONE")));
            aluno.setSite(c.getString(c.getColumnIndex("SITE")));
            aluno.setNota(c.getDouble(c.getColumnIndex("NOTA")));
            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deletar(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("ALUNOS", "ID = ?", params);
    }
}
