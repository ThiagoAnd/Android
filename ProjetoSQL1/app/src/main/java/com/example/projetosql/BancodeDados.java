package com.example.projetosql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancodeDados extends SQLiteOpenHelper {

    private static BancodeDados instance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BancoInteracao.sqlite";

    private Context context;

    public BancodeDados(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    public static BancodeDados Sharedinstance(Context context) {
        if (instance == null) {
            instance = new BancodeDados(context);
        }
        return instance;

    }

    ;

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query = "CREATE TABLE IF NOT EXISTS TbAluno (Matricula INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT NOT NULL, Email TEXT,CPF TEXT,Endereco TEXT, Telefone TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbDisciplinas (_CodDisciplina INTEGER PRIMARY KEY AUTOINCREMENT,CodPeriodo INTEGER UNIQUE,entNomeDisciplina TEXT NOT NULL, entCargaHoraria TEXT, FOREIGN KEY (CodPeriodo) REFERENCES TbPeriodo(CodPeriodo))";
        db.execSQL(query);

       // query = "CREATE TABLE IF NOT EXISTS TbAlunoDisciplina (_CodDisciplina INTEGER ,_Matricula INTEGER, PRIMARY KEY (_Matricula, _CodDisciplina), FOREIGN KEY (_Matricula) REFERENCES TbAluno(Matricula) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (_CodDisciplina) REFERENCES TbDisciplina(_CodDisciplina) ON DELETE CASCADE ON UPDATE NO ACTION )";
       // db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbPeriodo (CodPeriodo INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT,DataInicio TEXT,DataFim TEXT )";
        db.execSQL(query);


        query = "CREATE TABLE IF NOT EXISTS TbUsuario (CodUsuario INTEGER PRIMARY KEY AUTOINCREMENT,Login TEXT NOT NULL ,Senha TEXT NOT NULL, UNIQUE (Login))";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbCurso (CodCurso INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT NOT NULL ,Turno TEXT NOT NULL, UNIQUE (Nome,Turno))";
        db.execSQL(query);

        /*, FOREIGN KEY (CodMatricula) REFERENCES TbAluno(Matricula),FOREIGN KEY (CodCurso) REFERENCES TbCurso(CodCurso), FOREIGN KEY (CodPeriodo) REFERENCES TbPerodo(CodPeriodo)*/
        query = "CREATE TABLE IF NOT EXISTS TbMatricula (IdMatricula INTEGER PRIMARY KEY AUTOINCREMENT,CodMatricula INTEGER ,DataMatricula TEXT, CodCurso INTEGER, CodPeriodo INTEGER, UNIQUE(CodMatricula,CodCurso,CodPeriodo),FOREIGN KEY (CodMatricula) REFERENCES TbAluno(Matricula),FOREIGN KEY (CodCurso) REFERENCES TbCurso(CodCurso), FOREIGN KEY (CodPeriodo) REFERENCES TbPeriodo(CodPeriodo))";
        db.execSQL(query);

    }

    public long insertAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();


        values.put("Nome", aluno.getNome());
        values.put("Email", aluno.getEmail());
        values.put("CPF", aluno.getCPF());
        values.put("Endereco", aluno.getEndereco());
        values.put("Telefone", aluno.getTelefone());

        long insertAluno = db.insert("TbAluno", "", values);
        db.close();
        return insertAluno;

    }

    public  List<Periodo> buscarPeriodos(String dados){

        List<Periodo> lista = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM TbPeriodo WHERE CodPeriodo = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{"%"+dados+"%"});

        if (cursor.moveToFirst()) {
            do {
                Periodo periodo = new Periodo();
                periodo.setCodPeriodo(cursor.getInt(cursor.getColumnIndex("CodPeriodo")));
                periodo.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                periodo.setDataInicio(cursor.getString(cursor.getColumnIndex("DataInicio")));
                periodo.setDataFim(cursor.getString(cursor.getColumnIndex("DataFim")));

                lista.add(periodo);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lista;

    }

    public  List<Periodo> listarPeriodos(){
        List<Periodo> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbPeriodo ORDER BY Nome";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Periodo periodo = new Periodo();
                periodo.setCodPeriodo(cursor.getInt(cursor.getColumnIndex("CodPeriodo")));
                periodo.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                periodo.setDataInicio(cursor.getString(cursor.getColumnIndex("DataInicio")));
                periodo.setDataFim(cursor.getString(cursor.getColumnIndex("DataFim")));

                lista.add(periodo);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lista;


    }

    public List<Curso> listarCursos() {
        List<Curso> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbCurso ORDER BY Nome";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Curso curso = new Curso();
                curso.setCodCurso(cursor.getInt(cursor.getColumnIndex("CodCurso")));
                curso.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                curso.setTurno(cursor.getString(cursor.getColumnIndex("Turno")));

                lista.add(curso);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lista;
    }


    public int checarLogin(String login, String senha) {
        int auxAutenticacao = 0;

        SQLiteDatabase db = this.getWritableDatabase();

       /* String sql = "SELECT * FROM TbUsuario WHERE Login =+login+ AND ";*/

        String sql = "SELECT * FROM TbUsuario";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                if ((cursor.getString(cursor.getColumnIndex("Login")).equals(login)) && (cursor.getString(cursor.getColumnIndex("Senha")).equals(senha))) {
                auxAutenticacao = 1;
                }else auxAutenticacao = -1;
            } while (cursor.moveToNext());

        }
        db.close();
        return auxAutenticacao;

    }

    public long insertUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        values.put("Login", usuario.getLogin());
        values.put("Senha", usuario.getSenha());


        long insertUsuario = db.insert("TbUsuario", "", values);
        db.close();
        return insertUsuario;

    }


    public long insertCurso(Curso curso) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        values.put("Nome", curso.getNome());
        values.put("Turno", curso.getTurno());


        long insertCurso = db.insert("TbCurso", "", values);
        db.close();
        return insertCurso;

    }

    public long insertMatricula(Matricula matricula) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        values.put("CodMatricula", matricula.getCodMatricula());
        values.put("DataMatricula", matricula.getDataMatricula());
        values.put("CodCurso", matricula.getCodCurso());
        values.put("CodPeriodo", matricula.getCodPeriodo());


        long insertMatricula = db.insert("TbMatricula", "", values);
        db.close();
        return insertMatricula;

    }

    public long inserirPeriodo(Periodo periodo) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();


        values.put("Nome", periodo.getNome());
        values.put("DataInicio", periodo.getDataInicio());
        values.put("DataFim", periodo.getDataFim());


        long insertPeriodo = db.insert("TbPeriodo", "", values);
        db.close();
        return insertPeriodo;

    }

    public long insertDisciplina(Disciplinas disciplina) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        values.put("CodPeriodo", disciplina.getCodPeriodo());
        values.put("entNomeDisciplina", disciplina.getEntNomeDisciplina());
        values.put("entCargaHoraria", disciplina.getEntCargaHoraria());

        long insertDisciplina = db.insert("TbDisciplinas", "", values);
        db.close();
        return insertDisciplina;

    }


    public void inserirAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        values.put("_Matricula", alunoDisciplina.get_Matricula());
        values.put("_CodDisciplina", alunoDisciplina.get_CodDisciplina());


        long insert = db.insert("TbAlunoDisciplina", "", values);
        db.close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
