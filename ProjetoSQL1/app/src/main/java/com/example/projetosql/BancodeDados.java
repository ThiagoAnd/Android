package com.example.projetosql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

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
        query = "CREATE TABLE IF NOT EXISTS TbAluno (Matricula INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT NOT NULL UNIQUE, Email TEXT UNIQUE,CPF TEXT UNIQUE,Endereco TEXT, Telefone TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbDisciplinas (_CodDisciplina INTEGER PRIMARY KEY AUTOINCREMENT,CodPeriodo INTEGER ,entNomeDisciplina TEXT NOT NULL, entCargaHoraria TEXT,UNIQUE(CodPeriodo,entNomeDisciplina), FOREIGN KEY (CodPeriodo) REFERENCES TbPeriodo(CodPeriodo))";
        db.execSQL(query);

        // query = "CREATE TABLE IF NOT EXISTS TbAlunoDisciplina (_CodDisciplina INTEGER ,_Matricula INTEGER, PRIMARY KEY (_Matricula, _CodDisciplina), FOREIGN KEY (_Matricula) REFERENCES TbAluno(Matricula) ON DELETE CASCADE ON UPDATE NO ACTION, FOREIGN KEY (_CodDisciplina) REFERENCES TbDisciplina(_CodDisciplina) ON DELETE CASCADE ON UPDATE NO ACTION )";
        // db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbPeriodo (CodPeriodo INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT UNIQUE,DataInicio TEXT,DataFim TEXT )";
        db.execSQL(query);


        query = "CREATE TABLE IF NOT EXISTS TbUsuario (CodUsuario INTEGER PRIMARY KEY AUTOINCREMENT,Login TEXT NOT NULL ,Senha TEXT NOT NULL, UNIQUE (Login))";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbCurso (CodCurso INTEGER PRIMARY KEY AUTOINCREMENT,Nome TEXT NOT NULL ,Turno TEXT NOT NULL, UNIQUE (Nome,Turno))";
        db.execSQL(query);

        /*, FOREIGN KEY (CodMatricula) REFERENCES TbAluno(Matricula),FOREIGN KEY (CodCurso) REFERENCES TbCurso(CodCurso), FOREIGN KEY (CodPeriodo) REFERENCES TbPerodo(CodPeriodo)*/
        query = "CREATE TABLE IF NOT EXISTS TbMatricula (IdMatricula INTEGER PRIMARY KEY AUTOINCREMENT,CodMatricula INTEGER ,DataMatricula TEXT, CodCurso INTEGER, CodPeriodo INTEGER, UNIQUE(CodMatricula,CodCurso,CodPeriodo),FOREIGN KEY (CodMatricula) REFERENCES TbAluno(Matricula),FOREIGN KEY (CodCurso) REFERENCES TbCurso(CodCurso), FOREIGN KEY (CodPeriodo) REFERENCES TbPeriodo(CodPeriodo))";
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS TbNota (Id INTEGER PRIMARY KEY AUTOINCREMENT,CodMatricula INTEGER ,CodDisciplina INTEGER, Nota1 REAL,Nota2 REAL,FOREIGN KEY (CodMatricula) REFERENCES TbAluno(Matricula),FOREIGN KEY (CodDisciplina) REFERENCES TbDisciplinas(_CodDisciplina))";
        db.execSQL(query);

    }

    public long adicionarNota(String nota1, String nota2, int idDisciplina, int idAluno) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("PRAGMA foreign_keys = ON;");

        ContentValues values = new ContentValues();

        db.execSQL("DELETE FROM TbNota WHERE CodMatricula = " + idAluno + " AND CodDisciplina = " + idDisciplina + "");

        values.put("CodMatricula", idAluno);
        values.put("CodDisciplina", idDisciplina);
        values.put("Nota1", nota1);
        values.put("Nota2", nota2);

        long insertNotas = db.insert("TbNota", "", values);
        db.close();
        return insertNotas;


    }

    public Nota verificarRegistroNotas(int idDisciplina, int idAluno) {
        Nota nota = null;

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbNota ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getInt(cursor.getColumnIndex("CodMatricula")) == idAluno)&& (cursor.getInt(cursor.getColumnIndex("CodDisciplina"))==idDisciplina)) {
                    nota= new Nota();
                    nota.setNota1(cursor.getDouble(cursor.getColumnIndex("Nota1")));
                    nota.setNota2(cursor.getDouble(cursor.getColumnIndex("Nota2")));
                    break;
                }
            } while (cursor.moveToNext());

        }
        //cursor.close();
        db.close();
        return nota;

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

    public String buscarCodPeriodo(String nomePeriodo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String codigo = "999";

        db.execSQL("PRAGMA foreign_keys = ON;");

        String sql = "SELECT * FROM TbPeriodo ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Periodo periodo = new Periodo();
                if ((cursor.getString(cursor.getColumnIndex("Nome")).equals(nomePeriodo))) {
                    return cursor.getString(cursor.getColumnIndex("CodPeriodo"));
                    // return "entrou";
                }
            } while (cursor.moveToNext());

        }
        db.close();

        return codigo;

    }

    public String buscarCodAluno(String nomeAluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        String codigo = "777";

        db.execSQL("PRAGMA foreign_keys = ON;");

        String sql = "SELECT * FROM TbAluno ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {

                if ((cursor.getString(cursor.getColumnIndex("Nome")).equals(nomeAluno))) {
                    return cursor.getString(cursor.getColumnIndex("Matricula"));
                    // return "entrou";
                }
            } while (cursor.moveToNext());

        }
        db.close();

        return codigo;

    }

    public String buscarCodCurso(String nomeCurso) {
        SQLiteDatabase db = this.getWritableDatabase();
        String codigo = "888";

        db.execSQL("PRAGMA foreign_keys = ON;");

        String sql = "SELECT * FROM TbCurso ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {

                if ((cursor.getString(cursor.getColumnIndex("Nome")).equals(nomeCurso))) {
                    return cursor.getString(cursor.getColumnIndex("CodCurso"));
                    // return "entrou";
                }
            } while (cursor.moveToNext());

        }
        db.close();

        return codigo;

    }


    public List<Periodo> buscarPeriodos(String dados) {

        List<Periodo> lista = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM TbPeriodo WHERE CodPeriodo = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{"%" + dados + "%"});

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

    public List<Aluno> listarAlunos() {
        List<Aluno> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbAluno ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                aluno.setMatricula(cursor.getInt(cursor.getColumnIndex("Matricula")));
                aluno.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                aluno.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                aluno.setEndereco(cursor.getString(cursor.getColumnIndex("Endereco")));
                aluno.setCPF(cursor.getString(cursor.getColumnIndex("CPF")));
                aluno.setTelefone(cursor.getString(cursor.getColumnIndex("Telefone")));

                lista.add(aluno);
            } while (cursor.moveToNext());

        }
        //cursor.close();
        db.close();
        return lista;


    }

    public List<Periodo> listarPeriodos() {
        List<Periodo> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbPeriodo";

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
        //cursor.close();
        db.close();
        return lista;


    }

    public List<Disciplinas> listarDisciplinas() {
        List<Disciplinas> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbDisciplinas ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Disciplinas disciplina = new Disciplinas();
                disciplina.set_CodDisciplina(cursor.getInt(cursor.getColumnIndex("_CodDisciplina")));
                disciplina.setCodPeriodo(cursor.getInt(cursor.getColumnIndex("CodPeriodo")));
                disciplina.setEntNomeDisciplina(cursor.getString(cursor.getColumnIndex("entNomeDisciplina")));
                disciplina.setEntCargaHoraria(cursor.getString(cursor.getColumnIndex("entCargaHoraria")));

                lista.add(disciplina);
            } while (cursor.moveToNext());

        }
        //cursor.close();
        db.close();
        return lista;


    }

    public List<Matricula> listarMatriculas() {
        List<Matricula> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbMatricula";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(cursor.getInt(cursor.getColumnIndex("IdMatricula")));
                matricula.setCodMatricula(cursor.getInt(cursor.getColumnIndex("CodMatricula")));
                matricula.setCodPeriodo(cursor.getInt(cursor.getColumnIndex("CodPeriodo")));
                matricula.setCodCurso(cursor.getInt(cursor.getColumnIndex("CodCurso")));
                matricula.setDataMatricula(cursor.getString(cursor.getColumnIndex("DataMatricula")));

                lista.add(matricula);
            } while (cursor.moveToNext());

        }
        //cursor.close();
        db.close();
        return lista;


    }

    public ArrayAdapter<Curso> listarCursosAdapter() {
        List<Curso> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbCurso ";

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
        return (ArrayAdapter<Curso>) lista;
    }
    public String checkNomeDisciplina(int codDisciplina) {
        String nomeDisciplina = "fora";

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbDisciplinas  ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex("_CodDisciplina"))==codDisciplina){
                    nomeDisciplina = cursor.getString(cursor.getColumnIndex("entNomeDisciplina"));
                    break;
                }

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return nomeDisciplina;
    }

    public List<Nota> listarNota(int codAluno) {
        List<Nota> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbNota ";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(cursor.getColumnIndex("CodMatricula"))==codAluno){
                    Nota nota= new Nota();
                    nota.setCodMatricula(cursor.getInt(cursor.getColumnIndex("CodMatricula")));
                    nota.setCodDisciplina(cursor.getInt(cursor.getColumnIndex("CodDisciplina")));
                    nota.setNota1(cursor.getDouble(cursor.getColumnIndex("Nota1")));
                    nota.setNota2(cursor.getDouble(cursor.getColumnIndex("Nota2")));
                    lista.add(nota);
                }

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return lista;
    }
    public List<Curso> listarCursos() {
        List<Curso> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM TbCurso ";

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


    public String verificarDataMatricula(int codigo) {
        String data = null;

        SQLiteDatabase db = this.getWritableDatabase();

        /* String sql = "SELECT * FROM TbUsuario WHERE Login =+login+ AND ";*/

        String sql = "SELECT * FROM TbMatricula";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex("CodMatricula")) == codigo) {
                    data = cursor.getString(cursor.getColumnIndex("DataMatricula"));
                    break;
                }
            } while (cursor.moveToNext());

        }
        db.close();
        return data;

    }

    public int checarMatriculado(int codigo) {
        int auxAutenticacao = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        /* String sql = "SELECT * FROM TbUsuario WHERE Login =+login+ AND ";*/

        String sql = "SELECT * FROM TbMatricula";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                if (cursor.getInt(cursor.getColumnIndex("CodMatricula")) == codigo) {
                    auxAutenticacao = 1;
                    break;
                }
            } while (cursor.moveToNext());

        }
        db.close();
        return auxAutenticacao;

    }


    public List<Disciplinas> listarDisciplinasPeriodo(int codPeriodo) {
        List<Disciplinas> lista = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        /* String sql = "SELECT * FROM TbUsuario WHERE Login =+login+ AND ";*/

        String sql = "SELECT * FROM TbDisciplinas";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {


                if (cursor.getInt(cursor.getColumnIndex("CodPeriodo")) == codPeriodo) {
                    Disciplinas disciplina = new Disciplinas();
                    disciplina.set_CodDisciplina(cursor.getInt(cursor.getColumnIndex("_CodDisciplina")));
                    disciplina.setCodPeriodo(cursor.getInt(cursor.getColumnIndex("CodPeriodo")));
                    disciplina.setEntNomeDisciplina(cursor.getString(cursor.getColumnIndex("entNomeDisciplina")));
                    disciplina.setEntCargaHoraria(cursor.getString(cursor.getColumnIndex("entCargaHoraria")));

                    lista.add(disciplina);
                }
            } while (cursor.moveToNext());

        }
        db.close();
        return lista;

    }

    public int verificarQtdDisciplinas(int codPeriodo) {
        int qtdDisciplinas = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        /* String sql = "SELECT * FROM TbUsuario WHERE Login =+login+ AND ";*/

        String sql = "SELECT * FROM TbDisciplinas";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {

                Disciplinas disciplina = new Disciplinas();
                if (cursor.getInt(cursor.getColumnIndex("CodPeriodo")) == codPeriodo) {
                    qtdDisciplinas += 1;
                }
            } while (cursor.moveToNext());

        }
        db.close();
        return qtdDisciplinas;

    }

    public int verificarPeriodo(int codigo) {
        int codPeriodo = 0;

        SQLiteDatabase db = this.getWritableDatabase();


        String sql = "SELECT * FROM TbMatricula";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(cursor.getColumnIndex("CodMatricula")) == codigo) {
                    codPeriodo = cursor.getInt(cursor.getColumnIndex("CodPeriodo"));
                    break;
                }
            } while (cursor.moveToNext());

        }
        db.close();
        return codPeriodo;

    }

    public int checarNota(int codAluno, int qtdDisciplinas) {
        int flag = 0;
        int cont =0;
        double x1 = 0.0;
        double x2 = 0.0;

        SQLiteDatabase db = this.getWritableDatabase();


        String sql = "SELECT * FROM TbNota";

        Cursor cursor = db.rawQuery(sql, new String[]{});

        if (cursor.moveToFirst()) {
            do {

                Nota nota = new Nota();

                if (cursor.getInt(cursor.getColumnIndex("CodMatricula")) == codAluno) {
                    cont +=1;
                    x1 = cursor.getFloat(cursor.getColumnIndex("Nota1"));
                    x2 = cursor.getFloat(cursor.getColumnIndex("Nota2"));
                    if ((x1 == 0.0) || (x2 == 0.0)) {
                        flag =2;
                    }
                }
            } while (cursor.moveToNext());

        }
        db.close();
       if ((cont==qtdDisciplinas)&&(flag!=2)){
            return 1;
       }
       if ((cont==0)&&(flag==0)){
           return 0;
       }
        return 2;


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
                } else auxAutenticacao = -1;
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
