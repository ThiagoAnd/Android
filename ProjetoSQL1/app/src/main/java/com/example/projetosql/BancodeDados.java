package com.example.projetosql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancodeDados  extends SQLiteOpenHelper {

    private static BancodeDados instance = null;

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "BancoInteracao.sqlite";

    private Context context;

    public BancodeDados(Context context){

    super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }



    public static BancodeDados Sharedinstance(Context context){
      if (instance == null){
          instance = new BancodeDados(context);
      }
      return instance;

    };

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query = "CREATE TABLE IF NOT EXISTS TbAluno (Matricula INTEGER PRIMARY KEY,Nome TEXT NOT NULL, Email TEXT, Telefone TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbDisciplinas (_CodDisciplina INTEGER PRIMARY KEY,entNomeDisciplina TEXT NOT NULL, entCargaHoraria TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbAlunoDisciplina (_CodDisciplina INTEGER ,_Matricula INTEGER, PRIMARY KEY (_Matricula, _CodDisciplina), FOREIGN KEY (_Matricula) REFERENCES TbAluno(_Matricula) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (_CodDisciplina) REFERENCES TbDisciplina(_CodDisciplina) ON DELETE CASCADE ON UPDATE NO ACTION )";
        db.execSQL(query);

    }

    public void insertAluno (Aluno aluno){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("Matricula",aluno.getMatricula());
        values.put("Nome", aluno.getNome());
        values.put("Email", aluno.getEmail());
        values.put("Telefone", aluno.getTelefone());

        long insert = db.insert("TbAluno", "", values);
        db.close();

    }

    public void insertDisciplina (Disciplinas disciplina){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("_CodDisciplina",disciplina.get_CodDisciplina());
        values.put("entNomeDisciplina", disciplina.getEntNomeDisciplina());
        values.put("entCargaHoraria", disciplina.getEntCargaHoraria());

        long insert = db.insert("TbDisciplinas", "", values);
        db.close();

    };

    public void inserirAlunoDisciplina (AlunoDisciplina alunoDisciplina){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("_Matricula",alunoDisciplina.get_Matricula());
        values.put("_CodDisciplina", alunoDisciplina.get_CodDisciplina());


        long insert = db.insert("TbAlunoDisciplina", "", values);
        db.close();

    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
